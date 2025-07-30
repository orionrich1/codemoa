package com.codemoa.project.domain.employment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codemoa.project.domain.employment.entity.Employment;
import com.codemoa.project.domain.employment.repository.EmploymentRepository;
import com.codemoa.project.domain.employment.service.EmploymentService;

@Controller
public class EmploymentController {
	
	private final EmploymentService employmentService;
	private final EmploymentRepository employmentRepository;
	
	
	public EmploymentController(EmploymentService employmentService, EmploymentRepository employmentRepository) {
		this.employmentService = employmentService;
		this.employmentRepository = employmentRepository;		
	}
	
	@GetMapping("/employmentList")
	public String getEmploymentList(
			@RequestParam(name = "region", required = false) String region,
			@RequestParam(name = "subRegion", required = false) String subRegion,
			@RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "page", defaultValue = "1") int page,			
			Model model ) {
		
		//페이지당 9개 게시굴 기준
		int pageSize = 9;
		Pageable pageable = PageRequest.of(page -1,  pageSize, Sort.by("recruitNo").descending());
		
		
		//조건에 따라 필터
		Page<Employment> employmentPage = employmentRepository
				.findByFilters(region, subRegion, keyword, pageable);
		
		  // 여기에 출력 코드 추가!
	    System.out.println("현재 페이지: " + page);
	    System.out.println("전체 페이지 수: " + employmentPage.getTotalPages());
	    System.out.println("전체 항목 수: " + employmentPage.getTotalElements());
		
		model.addAttribute("employmentList", employmentPage.getContent());		
		model.addAttribute("page", Map.of(
					"currentPage", page,
					"totalPages", employmentPage.getTotalPages()
			));
		
		return "views/employment/employmentList";
		
	}
}
