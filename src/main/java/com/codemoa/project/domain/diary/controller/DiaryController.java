//도영
package com.codemoa.project.domain.diary.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codemoa.project.domain.diary.service.DiaryService;
import com.codemoa.project.domain.user.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/my-pages/diary")
public class DiaryController {
	
	private final DiaryService diaryService;
	
	@GetMapping
	public String toMain() {
		return "redirect:/my-pages/diary/";
	}

	@GetMapping("/")
	public String myPage(Model model, @AuthenticationPrincipal CustomUserDetails principal) {
		model.addAttribute("diaryList", diaryService.getDiaryList(principal.getUsername()));
		return "views/diary/diaryList";
	}
}
