package com.codemoa.project.domain.employment.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.codemoa.project.domain.employment.entity.Employment;
import com.codemoa.project.domain.employment.repository.EmploymentRepository;

@Service
public class EmploymentCrawlingService {

	private final EmploymentRepository employmentRepository;
	
	public  EmploymentCrawlingService(EmploymentRepository employmentRepository) {
		this.employmentRepository = employmentRepository;
	}
	

	@GetMapping("/employment/crawl")
	public String crawlJobs() {
		employmentCrawlingService.crawlAll();
		return "redirect:/employmentList";
	}
	
	@Scheduled(cron = " 0 0 3 * * ?")
	public void autoCrawl() {
		crawlAll();
	}
	
	@Transactional
	public void crawlSaraminJobs() {
		String url = "https://www.saramin.co.kr/zf_user/jobs/list/job-category?cat_mcls=2&panel_type=&search_optional_item=n&search_done=y&panel_count=y&preview=y";
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements jobList = doc.select("div.item_recruit"); //사람인에서 각 채용공고 div
			
			int count = 0;
			
			for (Element job : jobList) {
				if(count >=30) break;
				
				String title = job.select("h2.job_tit a").text();
				String company = job.select("div.area_corp Strong.corp_name").text();
				String region = job.select("div.job_condition span").get(0).text();
				String detailUrl = "https://www.saramin.co.kr" + job.select("h2.job_tit a").attr("href");
				
				//중복 URL 저장 방지
				if(!employmentRepository.existsByUrl(detailUrl)) {
					Employment employment = new Employment();
					employment.setTitle(title);
					employment.setCompany(company);
					employment.setRegion(region);
					employment.setSubRegion(""); //사람인에서 세부 지역은 분리 되어있지 않음
					employment.setUrl(detailUrl);
					
					employmentRepository.save(employment);
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transactional
	public void crawlAlioJobs() {
		String url = "https://opendata.alio.go.kr/recruit/list?ongoingYn=Y&recrutPbancTtl=개발";
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements rows = doc.select("table tbody tr"); //알리오 테이블 구조로 가정
			
			int count = 0;
			
			for (Element row : rows) {
				if(count >=30) break;
				
				Elements columns = row.select("td");
				if(columns.size() < 4) continue;
				
				String company = columns.get(0).text(); //기관 명
				String title = columns.get(1).text(); //공고 제목
				String region = columns.get(2).text(); //지역
				String detailUrl = columns.get(1).select("a").attr("href"); //공고 링크
				
				//중복 URL 저장 방지
				if(!detailUrl.startsWith("http")) {
					detailUrl = "https://opendata.alio.go.kr" + detailUrl;
				}
				if(!employmentRepository.existsByUrl(detailUrl)) {
					Employment employment = new Employment();
					employment.setTitle(title);
					employment.setCompany(company);
					employment.setRegion(region);
					employment.setSubRegion(""); // 구체적 지역 정보 없을 경우 공란
					employment.setUrl(detailUrl);
					
					employmentRepository.save(employment);
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void crawlAll() {
		crawlSaraminJobs();
		crawlAlioJobs();
	}
	
}
