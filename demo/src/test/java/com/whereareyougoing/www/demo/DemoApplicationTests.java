package com.whereareyougoing.www.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import com.whereareyougoing.www.demo.config.CsvReader;
import com.whereareyougoing.www.demo.cs.service.DistrictService;
import com.whereareyougoing.www.demo.data.CsvDataSorter;
import com.whereareyougoing.www.demo.dd.domain.District;
import com.whereareyougoing.www.demo.dd.domain.Flow;
import com.whereareyougoing.www.demo.dd.dto.AgeDataDto;
import com.whereareyougoing.www.demo.dd.dto.DistrictDataDto;
import com.whereareyougoing.www.demo.dd.dto.GenderDataDto;
import com.whereareyougoing.www.demo.dd.dto.SevenDayDataDto;
import com.whereareyougoing.www.demo.dd.dto.TimeDataDto;
import com.whereareyougoing.www.demo.dd.dto.YearDataDto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Rollback(value = false)
class DemoApplicationTests {

	String url = "http://openapi.seoul.go.kr:8088/Apikey/json/VwsmTrdhlFlpopQq/1/5/2017";

	// // 외부 url을 호출할때는 RestTemplate를 써야한다 mockMvc는 컨트롤러를 호출할 때 쓰인다
	// @Autowired
	// RestTemplate restTemplate;
	// @Test
	// @DisplayName("api 호출 테스트2")
	// void apiCallTest() throws Exception {
	// 	restTemplate = new RestTemplate();
	// 	ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
	// 	JSONParser jsonParser = new JSONParser();
	// 	JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody().toString());
	// 	jsonObject = (JSONObject) jsonObject.get("VwsmTrdhlFlpopQq");
	// 	JSONArray array = (JSONArray) jsonObject.get("row");
	// 	JSONObject jsonObject2 = (JSONObject) array.get(0);
	// 	System.out.println(jsonObject2.get("FAG_30_THUTM_2_FLPOP_CO"));
	// }

	@Autowired
	CsvDataSorter csvDataSorter;
	@Autowired
	CsvReader csvReader;

	@Autowired
	DistrictService districtService;
	
	@Test
	@DisplayName("영속화 테스트")
	@Transactional
	public void persistTest() {

		csvReader.setfileName("data.csv");
		List<String[]> data = new ArrayList<String[]>();
		// data 를 이용해서 row를 한줄씩 읽을 수 있다
		data = csvReader.readCsv();
		Iterator<String[]> dataIter = data.iterator();
		dataIter.next(); // 컬럼명들은 필요없기 때문에 Skip 한다

		int code=0;
		List<Integer> codes=new ArrayList<>();
		codes.add(0);
		while (dataIter.hasNext()) {

			List<String> dataList = csvDataSorter.arrayToList(dataIter.next());

			//
			DistrictDataDto districtDataDto = csvDataSorter.extractDistrictData(dataList);
			
			//1001010(14년도 마지막) vs 1000001(15년도 맨처음) 
			//결과 다르기 때문에 1000001을 새로 생성함  그 다음에는  1000001(15년맨처음) 1000001(15년 맨처음 다음) 이니 서로 같다고 판단해서
			//새로 안만들고 1000001를 찾을려고함 근데 이미 14년도 1000001와 15년도 1000001가 있기 때문에 유니크하지 않고 에러가 발생했다

			//csv 파일을  상권코드로 정렬했으면 에러가 안났을거고 결국에 시간을 절약했는데 어쨋뜬 오류는 해결했다고 생각했는데 또 에러 발생해서 아에 다른 전략을 채택하기로 함

			
			
			if (code!=districtDataDto.getCode()){
				
				code=districtDataDto.getCode();
				District district = District.builder().districtData(districtDataDto.toDistrictData()).build();

				//
				YearDataDto yearDataDto = csvDataSorter.extractYearData(dataList);

				GenderDataDto genderDataDto = csvDataSorter.extractGenderData(dataList);

				AgeDataDto ageDataDto = csvDataSorter.extractAgeData(dataList);

				TimeDataDto timeDataDto = csvDataSorter.extractTimeData(dataList);

				SevenDayDataDto sevenDayDataDto = csvDataSorter.extractSevenDayData(dataList);

				Flow flow = Flow.builder().yearData(yearDataDto.toYearData()).genderData(genderDataDto.toGenderData())
						.ageData(ageDataDto.toAgeData()).timeData(timeDataDto.toTimeData())
						.sevenDayData(sevenDayDataDto.toSevenDayData()).build();

				//
				district.addFlow(flow);
				districtService.save(district);
			} else {
				District district = districtService.findOneByCode(code);
				YearDataDto yearDataDto = csvDataSorter.extractYearData(dataList);

				GenderDataDto genderDataDto = csvDataSorter.extractGenderData(dataList);

				AgeDataDto ageDataDto = csvDataSorter.extractAgeData(dataList);

				TimeDataDto timeDataDto = csvDataSorter.extractTimeData(dataList);

				SevenDayDataDto sevenDayDataDto = csvDataSorter.extractSevenDayData(dataList);

				Flow flow = Flow.builder().yearData(yearDataDto.toYearData()).genderData(genderDataDto.toGenderData())
						.ageData(ageDataDto.toAgeData()).timeData(timeDataDto.toTimeData())
						.sevenDayData(sevenDayDataDto.toSevenDayData()).build();

				//
				district.addFlow(flow);

			}

		}
	}
	




}



