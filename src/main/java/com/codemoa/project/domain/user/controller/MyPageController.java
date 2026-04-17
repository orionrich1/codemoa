package com.codemoa.project.domain.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemoa.project.domain.diary.dto.request.SaveProjectRequest;
import com.codemoa.project.domain.diary.entity.Project;
import com.codemoa.project.domain.diary.service.DiaryService;
import com.codemoa.project.domain.problem.service.ProblemService;
import com.codemoa.project.domain.support.service.SupportService;
import com.codemoa.project.domain.user.dto.request.UserPassUpdateRequest;
import com.codemoa.project.domain.user.dto.request.UserUpdateRequest;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.repository.UserRepository;
import com.codemoa.project.domain.user.security.CustomUserDetails;
import com.codemoa.project.domain.user.service.MyPageService;
import com.codemoa.project.domain.user.service.SnsUserService;
import com.codemoa.project.domain.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/my-pages")
@RequiredArgsConstructor
public class MyPageController {
	private final MyPageService myPageService;
	private final SnsUserService snsUserService;
	private final UserService userService;
	private final DiaryService diaryService;
	private final ProblemService problemService;
	private final SupportService supportService;

	private final UserRepository userRepository;

	@GetMapping
	public String toMain() {
		return "redirect:/my-pages/";
	}

	// ======================================
	// 마이 페이지 관련
	// ======================================

	@GetMapping("/")
	public String myPage(Model model, @AuthenticationPrincipal CustomUserDetails principal,
			@RequestParam(value = "keyword", required = false) String keyword) {
		User user = principal.getUser();
		model.addAttribute("myPageUser", myPageService.checkSnsLinked(user));

		String userId = principal.getUsername();
		if (keyword != null) {
			model.addAttribute("projects", diaryService.searchProjectList(userId, keyword));
			model.addAttribute("searched", true);
		} else {
			model.addAttribute("projects", diaryService.getProjectList(userId));
		}

		model.addAttribute("activityAiSubmissions7d", problemService.countSubmissionsLast7Days(userId));
		model.addAttribute("activityMyUnansweredQna", supportService.countMyUnansweredQna(userId));
		model.addAttribute("activityProjectsInProgress", diaryService.countProjectsByStatus(userId, "진행중"));

		return "views/user/mypage/myPageMain";
	}

	@PostMapping("/snsUnlink")
	public String snsUnlink(@AuthenticationPrincipal CustomUserDetails principal) {
		User user = principal.getUser();
		snsUserService.unlinkSnsAccount(user.getUserId());
		return "redirect:/my-pages/";
	}

	@GetMapping("/updateUserForm")
	public String updateUserForm(Model model, @AuthenticationPrincipal CustomUserDetails principal) {
		User user = principal.getUser();
		UserUpdateRequest form = new UserUpdateRequest();
		form.setName(user.getName());
		form.setNickname(user.getNickname());
		form.setMobile(user.getMobile());
		form.setEmail(user.getEmail());
		model.addAttribute("userUpdateRequest", form);
		model.addAttribute("user", user);
		return "views/user/mypage/updateForm";
	}

	@ResponseBody
	@PostMapping("/checkPass")
	public Map<String, String> checkPass(UserPassUpdateRequest request,
			@AuthenticationPrincipal CustomUserDetails principal) {
		Map<String, String> map = new HashMap<>();
		// C-2: 클라이언트가 보낸 userId를 무시하고 세션의 인증된 userId를 사용
		request.setUserId(principal.getUsername());
		String result = myPageService.checkPass(request);
		map.put("result", result);
		return map;
	}

	@PostMapping("/updateUser")
	public String updateUser(@Valid @ModelAttribute("userUpdateRequest") UserUpdateRequest request,
			BindingResult bindingResult, Model model, @AuthenticationPrincipal CustomUserDetails principal) {
		request.setUserId(principal.getUsername());
		if (bindingResult.hasErrors()) {
			model.addAttribute("user", principal.getUser());
			return "views/user/mypage/updateForm";
		}
		userService.updateUser(request);

		User updatedUser = userRepository.findById(request.getUserId()).orElseThrow();
		CustomUserDetails updatedDetails = new CustomUserDetails(updatedUser);
		Authentication newAuth = new UsernamePasswordAuthenticationToken(updatedDetails, null,
				updatedDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(newAuth);

		return "redirect:/my-pages/";
	}

	// C-4: GET → POST 로 변경하여 링크 클릭만으로 계정 삭제되는 취약점 방지
	@PostMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request, HttpServletResponse response, Authentication authentication,
			@AuthenticationPrincipal CustomUserDetails principal) {
		User user = principal.getUser();

		new SecurityContextLogoutHandler().logout(request, response, authentication);
		userService.deleteUser(user.getUserId());
		return "redirect:/";
	}

	// ======================================
	// 프로젝트 관리 관련
	// ======================================

	// 프로젝트 상세 정보
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

	// 프로젝트 작성, 수정 폼
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

	// 프로젝트 작성, 수정 요청
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
		return "redirect:/my-pages/";
	}

	// C-4: GET → POST 로 변경
	@PostMapping("/deleteProject/{projectId}")
	public String deleteProject(@PathVariable(value = "projectId") int projectId, RedirectAttributes redirectAttributes,
			@AuthenticationPrincipal CustomUserDetails principal) {
		Project project = diaryService.getProjectDetail(projectId);
		if (principal.getUsername().equals(project.getUserId())) {
			diaryService.deleteProject(projectId);
			return "redirect:/my-pages/";
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "자신의 프로젝트만 삭제할 수 있습니다.");
			return "redirect:/my-pages/";
		}
	}

}
