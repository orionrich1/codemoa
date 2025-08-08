package com.codemoa.project.domain.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codemoa.project.domain.main.service.MainService;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final MainService mainService;

	/*
	 * 메인 페이지를 반환합니다.
	 * @return views/main/index
	 */
	@GetMapping("/")
	public String mainPage() {
		// templates 폴더를 기준으로 /views/main/index.html 파일을 찾아 반환합니다.
		return "views/main/index";
	}

	@GetMapping("/search")
    public String search(@RequestParam ("keyword") String keyword, Model model) {
    	model.addAttribute("searchList", mainService.searchAll(keyword));
    	return "views/main/search";
    }
}