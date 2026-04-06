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
import com.codemoa.project.domain.employment.service.EmploymentApiService;
import com.codemoa.project.domain.employment.service.EmploymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EmploymentController {

	private static final int PAGE_SIZE = 9;
	private static final int PAGE_GROUP = 10;

	private final EmploymentApiService employmentApiService;
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

		type = normalizeQuery(type);
		keyword = normalizeQuery(keyword);
		int safePage = Math.max(1, page);

		Pageable pageable = PageRequest.of(safePage - 1, PAGE_SIZE, Sort.unsorted());
		Page<EmploymentDto> employmentPage = employmentService.getEmploymentWithFilters(type, keyword, pageable);
		Map<String, Integer> pagination = employmentService.buildPagination(safePage,
				(int) employmentPage.getTotalPages(), PAGE_GROUP);

		log.debug("채용 목록 조회 — page={}, totalPages={}, totalElements={}",
				safePage, employmentPage.getTotalPages(), employmentPage.getTotalElements());

		model.addAttribute("employmentList", employmentPage.getContent());
		model.addAttribute("type", type != null ? type : "");
		model.addAttribute("keyword", keyword != null ? keyword : "");
		model.addAttribute("pagination", pagination);
		model.addAttribute("latestDatasetRegDt", employmentService.getLatestDatasetRegDt().orElse(null));

		return "views/employment/employmentList";
	}

	private static String normalizeQuery(String value) {
		if (value == null) {
			return null;
		}
		String v = value.trim();
		if (v.isEmpty() || "null".equalsIgnoreCase(v)) {
			return null;
		}
		return v;
	}

	/**
	 * 목록의 「정보 갱신」은 GET 폼으로 호출. Spring Security 에서 ROLE_ADMIN 만 허용.
	 */
	@GetMapping("/employment/crawl")
	public String crawlEmploymentData() {
		employmentApiService.fetchAndSaveEmploymentList(1, 300);
		return "redirect:/employmentList";
	}
}
