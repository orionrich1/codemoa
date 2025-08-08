package com.codemoa.project.domain.employment.controller;

import java.util.Map;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codemoa.project.domain.employment.dto.response.EmploymentDto;
import com.codemoa.project.domain.employment.repository.EmploymentRepository;
import com.codemoa.project.domain.employment.service.EmploymentApiService;
import com.codemoa.project.domain.employment.service.EmploymentService;


@Controller
public class EmploymentController {
	
  
	private final EmploymentApiService employmentApiService;
	private final EmploymentRepository employmentRepository;
	private final EmploymentService employmentService;
	
	
	public EmploymentController(EmploymentApiService employmentApiService, 
			EmploymentRepository employmentRepository,
			EmploymentService employmentService) {
		this.employmentApiService = employmentApiService;
		this.employmentRepository = employmentRepository;	
		this.employmentService = employmentService;		
	}
	
	//API 호출해서 DB 저장(수동 실행용)
	@PostMapping("/employmentfetch")
	public String fetchEmpolymentDate(
			@RequestParam(name = "startPage", defaultValue = "1") int startPage,
			@RequestParam(name = "display", defaultValue = "10") int display,
			Model model
			) {
		employmentApiService.fetchAndSaveEmploymentList(startPage, display);
		return "redirect:/employmentList";
	}
		
	//채용 리스트
	@GetMapping("/employmentList")
	public String getEmploymentList(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", defaultValue = "1") int page,			
			Model model ) {
		
		//페이지당 9개 게시글 기준
		int pageSize = 9;
		Pageable pageable = PageRequest.of(page -1,  pageSize, Sort.by("recruitNo").descending());

		//조건에 따라 필터
		Page<EmploymentDto> employmentPage = employmentService.getEmploymentWithFilters(type, keyword, pageable);	
		
		Map<String, Integer> pagination = employmentService.getPaginationInfo(page);
		  // 여기에 출력 코드 추가!
	    System.out.println("현재 페이지: " + page);
	    System.out.println("전체 페이지 수: " + employmentPage.getTotalPages());
	    System.out.println("전체 항목 수: " + employmentPage.getTotalElements());
		
	    System.out.println("employmentList size: " + employmentPage.getContent().size());
	    
		for (EmploymentDto e : employmentPage.getContent()) {
		    System.out.println(e.getTitle() + " / " + e.getCompany());
		}
	    
		model.addAttribute("employmentList", employmentPage.getContent());	
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pagination", pagination);
	
		return "views/employment/employmentList";		
	}
	
	@GetMapping("/employment/crawl")
	public String crawlEmploymentDate(){
		  employmentApiService.fetchAndSaveEmploymentList(1, 300); // 페이지: 1, 10건 가져오기

		    return "redirect:/employmentList"; // 크롤링 후 목록 페이지로 리다이렉트
	}
	
		
}
