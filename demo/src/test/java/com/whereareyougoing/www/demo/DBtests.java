package com.whereareyougoing.www.demo;

import java.util.List;
import javax.transaction.Transactional;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.whereareyougoing.www.demo.cs.service.DistrictService;
import com.whereareyougoing.www.demo.dd.domain.District;
import com.whereareyougoing.www.demo.dd.domain.QDistrict;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DBtests {

	@Autowired
	JPAQueryFactory qf;

	@Autowired
	DistrictService districtService;

	/*
	 * 테스트 목록 1.모든 지역을 보여준다 2. 페이징을 적용한다 3. 지역이름에 하이퍼링크를 걸어준다 4. 지역이름을 누르면 년도를
	 * 하이퍼링크로 보여준다
	 */
	@DisplayName("모든 지역명 보여주기 테스트")
	@Test
	public void showAllDistrictNameOnly() {
		List<District> result = districtService.findAll();
		result.forEach(e -> System.out.println("지역명 : " + e.getDistrictData().getStreetName()));
	}

	@Transactional
	@DisplayName("지역명+ 정보 보여주기 테스트")
	@Test
	public void showAllDistrictNameAndInfo() {
		List<District> result = qf.selectFrom(QDistrict.district).fetch();
		result.forEach(e -> {
			System.out.println("지역명 : " + e.getDistrictData().getStreetName());
			e.getFlows().forEach(i -> System.out.println("지역 유동정보: " + i));
		});
	}

	@Transactional
	@DisplayName("지역명 변수를 전달받아서 지역찾기 ")
	@Test
	public void findByStreetName() {
		String streetName = "계동길";
		District result = districtService.findByStreetName(streetName);
		System.out.println(result.getDistrictData());
	}
}