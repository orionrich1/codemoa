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
	
	@Transactional
	public void fetchAndSaveEmploymentList(int startPage, int display) {
		System.out.println("API 호출 시작");
        System.out.println("startPage: " + startPage + ", display: " + display);

		try {
			//API 요청 Url
			String apiUrl = "https://www.work24.go.kr/cm/openApi/call/wk/callOpenApiSvcInfo210L01.do"
					+ "?authKey=" + URLEncoder.encode(authKey, StandardCharsets.UTF_8)
					+ "&callTp=L" //목록 호출
					+ "&returnType=XML"
					+ "&startPage=" + startPage
					+ "&display=" + display;
			
			
			//URL 연결 및 XML 파싱
			InputStream is = new java.net.URL(apiUrl).openStream();
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			doc.getDocumentElement().normalize();
			
			//<wanted> 태그 목록 추출(채용 공고 각각)
			NodeList wantedList = doc.getElementsByTagName("wanted");
			System.out.println("공고 개수: " + wantedList.getLength());
			
			for (int i = 0; i< wantedList.getLength(); i++) {
				Element wanted = (Element) wantedList.item(i);
				
				String title = getTagValue(wanted, "title");
				String company = getTagValue(wanted, "company");
				String region = getTagValue(wanted, "region");
				String url = getTagValue(wanted, "wantedInfoUrl");
				
				System.out.println(">>> 공고: " + title + " / URL: " + url);
				
				//중복 url 체크후 저장
				if(url !=null) {
					boolean exists = employmentRepository.existsByUrl(url);
					 System.out.println("기존 존재 여부: " + exists);
					 if(!exists) {
					Employment employment = new Employment();
					employment.setTitle(title != null ? title : "정보 없음");
					employment.setCompany(company != null ? company : "정보 없음");
					employment.setRegion(region != null ? region : "정보 없음");
					employment.setSubRegion("");
					employment.setUrl(url);
					
					employmentRepository.save(employment);
					System.out.println(">>> 저장 완료: " + title);
				} else {
					System.out.println(">>> 중복 URL로 저장 생략");
					}
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
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
