package com.codemoa.project.domain.employment.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codemoa.project.domain.employment.dto.response.EmploymentDto;
import com.codemoa.project.domain.employment.repository.EmploymentRepository;
import com.codemoa.project.domain.employment.service.EmploymentApiService;
import com.codemoa.project.domain.employment.service.EmploymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// Q-1: System.out.println → Slf4j 로거
// Q-5: @RequiredArgsConstructor 으로 수동 생성자 교체
@Slf4j
@Controller
@RequiredArgsConstructor
public class EmploymentController {

	private final EmploymentApiService employmentApiService;
	private final EmploymentRepository employmentRepository;
	private final EmploymentService employmentService;

	@PostMapping("/employmentfetch")
	public String fetchEmploymentData(
			@RequestParam(name = "startPage", defaultValue = "1") int startPage,
			@RequestParam(name = "display", defaultValue = "10") int display,
			Model model) {
		employmentApiService.fetchAndSaveEmploymentList(startPage, display);
		return "redirect:/employmentList";
	}

	@GetMapping("/employmentList")
	public String getEmploymentList(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", defaultValue = "1") int page,
			Model model) {

		int pageSize = 9;
		Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("recruitNo").descending());
		Page<EmploymentDto> employmentPage = employmentService.getEmploymentWithFilters(type, keyword, pageable);
		Map<String, Integer> pagination = employmentService.getPaginationInfo(page);

		log.debug("채용 목록 조회 — page={}, totalPages={}, totalElements={}",
				page, employmentPage.getTotalPages(), employmentPage.getTotalElements());

		model.addAttribute("employmentList", employmentPage.getContent());
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pagination", pagination);

		return "views/employment/employmentList";
	}

	@PostMapping("/employment/crawl")
	public String crawlEmploymentData() {
		employmentApiService.fetchAndSaveEmploymentList(1, 300);
		return "redirect:/employmentList";
	}
}
