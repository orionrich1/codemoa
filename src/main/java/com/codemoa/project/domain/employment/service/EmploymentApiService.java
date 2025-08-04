package com.codemoa.project.domain.employment.service;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.*;

import com.codemoa.project.domain.employment.entity.Employment;
import com.codemoa.project.domain.employment.repository.EmploymentRepository;

import jakarta.transaction.Transactional;

@Service
public class EmploymentApiService {

	private final EmploymentRepository employmentRepository;
	
	//고용 24 API 인증키 (환경변수 또는 application.propertyies 로 관리 권장?)
	private static final String authKey = "4fcb876e-1e49-4f9e-ad8a-2a99c63315cd";
	
	public EmploymentApiService(EmploymentRepository employmentRepository) {
		this.employmentRepository = employmentRepository;
	}
	
	public void parseAndSaveEmploymentData(InputStream is, String source) throws Exception{

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(is);
		doc.getDocumentElement().normalize();
		
		//<wanted> 태그 목록 추출(채용 공고 각각)
		NodeList wantedList = doc.getElementsByTagName("dhsOpenEmpInfo");
		System.out.println("공고 개수: " + wantedList.getLength());
		
		for (int i = 0; i< wantedList.getLength(); i++) {
			Element wanted = (Element) wantedList.item(i);
			
			String wantedAuthNo = getTagValue(wanted, "empSeqno");  // 공고 고유번호
			   String title = getTagValue(wanted, "empWantedTitle");
			   String company = getTagValue(wanted, "empBusiNm");
			   String type = getTagValue(wanted, "empWantedTypeNm");
			   String startDate = getTagValue(wanted, "empWantedStdt");
			   String endDate = getTagValue(wanted, "empWantedEndt");
			   String url = getTagValue(wanted, "empWantedHomepgDetail");

			
			   System.out.println(">>> 공고: " + title + ", 회사: " + company + ", 유형: " + type + ", URL: " + url);
			
			   if (title != null && (
					   title.contains("개발자") || 
					   title.contains("IT") || 
					   title.contains("소프트웨어") ||
					   title.contains("프로그래머") ||
					   title.contains("엔지니어") ||
					   title.contains("백엔드") ||
					   title.contains("프론트엔드")					   
					   )) {
		
		
			//중복 url 체크후 저장
			if(url !=null) {
				boolean exists = employmentRepository.existsByUrl(url);
				 System.out.println("기존 존재 여부: " + exists);
				 if(!exists) {
					 Employment employment = new Employment();
					// ✅ 필드값 매핑
			            employment.setWantedAuthNo(wantedAuthNo != null ? wantedAuthNo : "정보 없음");
			            employment.setTitle(title != null ? title : "정보 없음");
			            employment.setCompany(company != null ? company : "정보 없음");
			            employment.setType(type != null ? type : "정보 없음");
			            employment.setStartDate(startDate != null ? startDate : "정보 없음");
			            employment.setEndDate(endDate != null ? endDate : "정보 없음");
			            employment.setRegDt(startDate != null ? startDate : "정보 없음"); // regDt와 startDate 둘 다 같은 값으로 저장
			            employment.setCloseDt(endDate != null ? endDate : "정보 없음");  // closeDt와 endDate 둘 다 같은 값으로 저장
			            employment.setRegion("정보 없음");      // API에 없으므로 임시값
			            employment.setSubRegion("정보 없음");   // API에 없으므로 임시값
			            employment.setUrl(url);
			            employment.setSource(source);
			            
			            
			            // 저장				
				System.out.println("저장 시도: " + employment.getTitle());
				employmentRepository.save(employment);
				System.out.println(">>> 저장 완료: " + title);
			} else {
				System.out.println(">>> 중복 URL로 저장 생략");
					}
				}
			}
		}
	}
	
	@Transactional
	public void fetchAndSaveEmploymentList(int startPage, int display) {
		System.out.println("API 호출 시작");
        System.out.println("startPage: " + startPage + ", display: " + display);

		try {					  
			//API 요청 Url	  https://www.work24.go.kr/cm/openApi/call/wk/callOpenApiSvcInfo210L21.do
			String apiUrl = "https://www.work24.go.kr/cm/openApi/call/wk/callOpenApiSvcInfo210L21.do"
					 + "?authKey=" + URLEncoder.encode(authKey, StandardCharsets.UTF_8)
				        + "&callTp=L"
				        + "&returnType=XML"
				        + "&startPage=" + startPage
				        + "&display=" + display;
				        //+ "&keyword=" + URLEncoder.encode("개발자", StandardCharsets.UTF_8);
						
			//URL 연결 및 XML 파싱
			InputStream is = new java.net.URL(apiUrl).openStream();
			parseAndSaveEmploymentData(is,"일반");			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void fetchAndSavePublicRecruitmentNews(int startPage, int display) {
		System.out.println("API 호출 시작");
        System.out.println("startPage: " + startPage + ", display: " + display);

		try {					  
			//API 요청 Url	 https://www.work24.go.kr/cm/openApi/call/wk/callOpenApiSvcInfo210L22.do
			String apiUrl = "https://www.work24.go.kr/cm/openApi/call/wk/callOpenApiSvcInfo210L22.do"
					 + "?authKey=" + URLEncoder.encode(authKey, StandardCharsets.UTF_8)
				        + "&callTp=L"
				        + "&returnType=XML"
				        + "&startPage=" + startPage
				        + "&display=" + display;
				        //+ "&keyword=" + URLEncoder.encode("개발자", StandardCharsets.UTF_8);
						
			//URL 연결 및 XML 파싱
			InputStream is = new java.net.URL(apiUrl).openStream();			
			parseAndSaveEmploymentData(is, "공채속보");			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	@Transactional
	public void fetchAndSaveMultiplePages(int totalPages, int displayPerPage) {
		for (int page= 1; page<= totalPages; page++) {
			System.out.println("=====크롤링 페이지" + page + " 시작 =========");
			fetchAndSaveEmploymentList(page, displayPerPage);
			System.out.println("=====크롤링 페이지" + page + " 종료 =========");
		}
	}
	
	
	//XML Element 에서 태그값 추출 헬퍼 메서드
	private String getTagValue(Element element, String tagName) {
		NodeList nl = element.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0 && nl.item(0).getFirstChild() != null) {
			return nl.item(0).getFirstChild().getNodeValue();
		}
		return null;
	}
	
	
}
