//도영
package com.codemoa.project.domain.diary.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemoa.project.domain.diary.dto.request.SaveProjectRequest;
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
	public String myPage(@RequestParam(value="keyword", required=false) String keyword, Model model,
			@AuthenticationPrincipal CustomUserDetails principal) {
		if (keyword != null) {
			model.addAttribute("projects", diaryService.searchProjectList(principal.getUsername(), keyword));
			model.addAttribute("searched", true);
		}
		else {
			model.addAttribute("projects", diaryService.getProjectList(principal.getUsername()));
		}
		
		return "views/diary/projectList";
	}

	@GetMapping("/projects")
	public String projectDetail(Model model, @RequestParam("id") Integer projectId,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal CustomUserDetails principal,
			HttpServletResponse response) {
		Project project = diaryService.getProjectDetail(projectId);

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

	@GetMapping("/projectForm/{projectId}")
	public String projectForm(@PathVariable(value = "projectId") int projectId, Model model,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal CustomUserDetails principal) {
		Project project = new Project();
		if (projectId != 0) {
			project = diaryService.getProjectDetail(projectId);
			if (!principal.getUsername().equals(project.getUserId())) {
				redirectAttributes.addFlashAttribute("errorMessage", "자신의 프로젝트만 수정할 수 있습니다.");
				return "redirect:/my-pages/";
			}
		}
		model.addAttribute("project", project);
		return "views/diary/projectForm";
	}

	@PostMapping("/projects")
	public String saveProject(@ModelAttribute SaveProjectRequest request,
			@AuthenticationPrincipal CustomUserDetails principal) {
		request.setUserId(principal.getUsername());

		if (request.getProjectId() == 0) {
			diaryService.addProject(request);
		}

		else {
			diaryService.updateProject(request);
		}

		return "redirect:/my-pages/diary/";
	}

	@GetMapping("/deleteProject/{projectId}")
	public String deleteProject(@PathVariable(value = "projectId") int projectId, RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal CustomUserDetails principal) {
		Project project = diaryService.getProjectDetail(projectId);
		if (principal.getUsername().equals(project.getUserId())) {
			diaryService.deleteProject(projectId);
			return "redirect:/my-pages/diary/";
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "자신의 프로젝트만 삭제할 수 있습니다.");
			return "redirect:/my-pages/";
		}

	}
}
