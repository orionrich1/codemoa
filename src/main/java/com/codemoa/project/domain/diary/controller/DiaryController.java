//도영
package com.codemoa.project.domain.diary.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemoa.project.domain.diary.entity.Project;
import com.codemoa.project.domain.diary.service.DiaryService;
import com.codemoa.project.domain.user.security.CustomUserDetails;

import jakarta.servlet.http.HttpServletResponse;
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
		model.addAttribute("projects", diaryService.getProjectList(principal.getUsername()));
		return "views/diary/projectList";
	}

	@GetMapping("/projects")
	public String projectDetail(Model model, @RequestParam("id") Integer projectId,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal CustomUserDetails principal,
			HttpServletResponse response) throws IOException {
		Project project = diaryService.getProjectDetail(projectId);

		principal.getUsername().trim().equals(project.getUserId().trim());
		if (principal.getUsername().equals(project.getUserId())) {
			model.addAttribute("project", project);
			model.addAttribute("checklist", diaryService.getProjectCheckList(projectId));
			model.addAttribute("diaries", diaryService.getProjectdiaries(projectId));
			return "views/diary/projectDetail";
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "자신의 프로젝트만 열람할 수 있습니다.");
			return "redirect:/my-pages/";
		}

	}
}
