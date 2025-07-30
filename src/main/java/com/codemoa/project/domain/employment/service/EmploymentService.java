package com.codemoa.project.domain.employment.service;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codemoa.project.domain.employment.entity.Employment;
import com.codemoa.project.domain.employment.repository.EmploymentRepository;

@Service
public class EmploymentService {
	
	
	private final EmploymentRepository employmentRepository;
	
	public EmploymentService(EmploymentRepository employmentRepository) {
		this.employmentRepository = employmentRepository;
	}
	
	@Transactional
	public void crawlAndSaveJobs() {
		try {
			String url = "https://www.saramin.co.kr/zf_user/jobs/list/job-category?cat_mcls=2";
			Document doc = Jsoup.connect(url).get();
			
			Elements jobCards = doc.select(".item_recruit");
			
			for (Element card : jobCards) {
				String title = card.select(".job_tit a").text();
				String company = card.select(".corp_name").text();
				String jobUrl = "https://www.saramin.co.kr" + card.select(".job_tit a").attr("href");
				String locationText = card.select(".job_condition span").text();
				
				//지역 정보 파싱
				String[] parts = locationText.split(" ");
				String region = parts.length > 0 ? parts[0] : "미정";
				String subRegion = parts.length > 1 ? parts[1] : "미정";
				
				Employment employment = new Employment();
				employment.setTitle(title);
				employment.setCompany(company);
				employment.setRegion(region);
				employment.setSubRegion(subRegion);
				employment.setUrl(jobUrl);
				
				employmentRepository.save(employment);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	
