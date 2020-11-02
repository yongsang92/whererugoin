package com.whereareyougoing.www.demo;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.halLinks;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseBody;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import com.whereareyougoing.www.demo.cs.service.DistrictService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureRestDocs
@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	DistrictService districtService;


	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation).uris().withScheme("https")
						.withHost("whereareyougoing.com").withPort(443))
				.apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation).operationPreprocessors()
						.withResponseDefaults(Preprocessors.prettyPrint())
						.withRequestDefaults(Preprocessors.prettyPrint()))
				.build();
	}

	/*
	 * 테스트 목록 1.모든 지역을 보여준다 2. 페이징을 적용한다 3. 지역이름에 하이퍼링크를 걸어준다 4. 지역이름을 누르면 년도를
	 * 하이퍼링크로 보여준다
	 */
	@DisplayName("DistrictController의 findAll 메서드 테스트 ")
	@Test
	public void streetNameToHateoasRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/districts")
				.header("Content-Type", "application/json;charset=UTF-8").accept(MediaTypes.HAL_JSON_VALUE))
				.andDo(MockMvcResultHandlers.print());
	}

	@DisplayName("도로명만 제공하는 테스트  ")
	@Test
	public void findAllStreetNames() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/districts/names")).andDo(MockMvcResultHandlers.print())
				.andDo(document("street-name-list"));
	}

	@DisplayName("DistrictController의 findAll 메서드 테스트 페이징 적용 ")
	@Test
	public void findAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/districts").param("page", "0").param("size", "10")
				.param("sort", "districtData.streetName,ASC").header("Content-Type", "application/json;charset=UTF-8")
				.accept(MediaTypes.HAL_JSON_VALUE))
				.andDo(document("paging",
						links(linkWithRel("first").description("맨 처음 페이지"), linkWithRel("self").description("현재 페이지"),
								linkWithRel("next").description("다음 페이지"), linkWithRel("last").description("마지막 페이지")),
						responseBody(beneathPath("_embedded.districtList")), responseBody(beneathPath("_links"))))
				.andDo(MockMvcResultHandlers.print());
	}

	@DisplayName("DistrictController의 findByName 메서드 테스트 ")
	@Test
	public void findByName() throws Exception {

		mockMvc.perform(RestDocumentationRequestBuilders.get("/district/{streetName}", "계동길")
				.header("Content-Type", "application/json;charset=UTF-8").accept(MediaTypes.HAL_JSON_VALUE))
				.andDo(document("index",
						links(halLinks(), linkWithRel("self").description("현재 구역이름"),
								linkWithRel("year").description("현재 구역이 가진 년도 정보"))))
				.andDo(MockMvcResultHandlers.print())
				
				
				.andDo(document("findByname-method's-parameter"
						// Path Variable을 문서화 할땐 RestDocumentationRequestBuilders를 써야한다 근데 prettyPrint 설정을 안했다 그래서 여기서 해준다
						, Preprocessors.preprocessResponse(Preprocessors.prettyPrint())

						// Path Variable값 문서화
						, RequestDocumentation.pathParameters(RequestDocumentation.parameterWithName("streetName")
								.description("지역명").attributes(key("constraints").value("")))

						// 관련 링크 문서화
						,links(
							linkWithRel("self").description("현재 링크"),
							linkWithRel("year").description("현재 지역이 가진 년도별 링크")
						)
						
						// 응답 본문 문서화 // 반복되는 값들 relaxed모드 적용
						,relaxedResponseFields(fieldWithPath("districtData.code").description("지역 코드"),
								fieldWithPath("districtData.streetName").description("지역 이름"),
								subsectionWithPath("flows[0].yearData").description("연도와 분기 정보"),
								subsectionWithPath("flows[0].genderData").description("남녀별 정보"),
								subsectionWithPath("flows[0].ageData").description("나이별 정보"),
								subsectionWithPath("flows[0].timeData").description("시간별 정보"),
								subsectionWithPath("flows[0].sevenDayData").description("요일별 정보")
						)

				));

	}

	@DisplayName("DistrictController의 findStreetNameWithYear 메서드 테스트 ")
	@Test
	public void findDistrictWithYear() throws Exception {

		mockMvc.perform(RestDocumentationRequestBuilders.get("/district/{streetName}/{year}", "계동길", 2014)
				.header("Content-Type", "application/json;charset=UTF-8").accept(MediaTypes.HAL_JSON_VALUE))
				.andDo(MockMvcResultHandlers.print())
				.andDo(document("findStreetNameWithYear-method's-parameter",
						Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
						
						// Path Variable값 문서화
						,pathParameters(
								parameterWithName("streetName").description("지역명").attributes(key("constraints").value(""))
								,parameterWithName("year").description("해당년도").attributes(key("constraints").value("2014년도 부터 시작합니다")))
						// 관련 링크 문서화
						,links(halLinks(),
							 linkWithRel("self").description("현재 링크")
							,linkWithRel("quarter").description("분기 링크")
						)	
						,PayloadDocumentation.responseBody(PayloadDocumentation.beneathPath("_embedded.flowList")
						)
				));
	}

	@DisplayName("DistrictController의 findStreetNameWithYearAndQuarter 메서드 테스트 ")
	@Test
	public void findStreetNameWithYearAndQuarter() throws Exception {
		mockMvc.perform(RestDocumentationRequestBuilders.get("/district/{streetName}/{year}/{quarter}", "계동길", 2015, 1)
				.header("Content-Type", "application/json;charset=UTF-8").accept(MediaTypes.HAL_JSON_VALUE))

				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(document("data-info"
				, Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
						
						// Path Variable값 문서화
						,pathParameters(
								parameterWithName("streetName").description("지역명").attributes(key("constraints").value(""))
								,parameterWithName("year").description("해당년도").attributes(key("constraints").value("2014년도 부터 시작"))
								,parameterWithName("quarter").description("분기").attributes(key("constraints").value("1분기~4분기"))
										
						)
						// 관련링크 문서화
						, links(linkWithRel("self").description("현재 링크"), linkWithRel("year").description("이전 링크"))
						
						// 응답 본문 문서화
						, relaxedResponseFields(
							fieldWithPath("regdate").description("등록 날짜")
							,fieldWithPath("updatedate").description("업데이트 날짜")
							,fieldWithPath("yearData.year").description("년도")
							,fieldWithPath("yearData.quarter").description("분기")
							,fieldWithPath("genderData.man").description("남성 유동인구")
							,fieldWithPath("genderData.woman").description("여성 유동인구")
							,fieldWithPath("ageData.teen").description("10대 유동인구")
							,fieldWithPath("ageData.twenty").description("20대 유동인구")
							,fieldWithPath("ageData.thirty").description("30대 유동인구")
							,fieldWithPath("ageData.forty").description("40대 유동인구")
							,fieldWithPath("ageData.fifty").description("50대 유동인구")
							,fieldWithPath("ageData.sixty").description("60대 유동인구")
							,fieldWithPath("timeData.time1").description("00:01~06:00 유동인구")
							,fieldWithPath("timeData.time2").description("06:01~11:00 유동인구")
							,fieldWithPath("timeData.time3").description("11:01~14:00 유동인구")
							,fieldWithPath("timeData.time4").description("14:01~17:00 유동인구")
							,fieldWithPath("timeData.time5").description("17:01~21:00 유동인구")
							,fieldWithPath("timeData.time6").description("21:01~24:00 유동인구")
							,fieldWithPath("sevenDayData.mon").description("월요일 유동인구")
							,fieldWithPath("sevenDayData.tue").description("화요일 유동인구")
							,fieldWithPath("sevenDayData.wen").description("수요일 유동인구")
							,fieldWithPath("sevenDayData.thu").description("목요일 유동인구")
							,fieldWithPath("sevenDayData.fri").description("금요일 유동인구")
							,fieldWithPath("sevenDayData.sat").description("토요일 유동인구")
							,fieldWithPath("sevenDayData.sun").description("일요일 유동인구")
							)
				));
	}
}