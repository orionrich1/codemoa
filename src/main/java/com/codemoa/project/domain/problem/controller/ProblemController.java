//도영
package com.codemoa.project.domain.problem.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import com.codemoa.project.domain.problem.dto.request.CodeSubmitRequest;
import com.codemoa.project.domain.problem.entity.Problem;
import com.codemoa.project.domain.problem.service.AiSupportService;
import com.codemoa.project.domain.problem.service.ProblemService;
import com.codemoa.project.domain.user.security.CustomUserDetails;
import com.google.genai.types.GenerateContentResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/problems")
@RequiredArgsConstructor
public class ProblemController {

	private final ProblemService problemService;
	private final AiSupportService aiSupportService;

	// /problems 까지만 입력 시에도 메인 페이지로 빠지게 설정
	@GetMapping
	public String redirect() {
		return "redirect:/problems/";
	}

	// 메인 페이지, 문제 리스트 출력
	@GetMapping("/")
	public String getProblemList(Model model) {
		model.addAttribute("problemList", problemService.getProblemList());
		return "views/problem/problemList";
	}

	// 상세 페이지, 문제 내용 및 답안 입력 화면
	@GetMapping("/problemDetail")
	public String getProblemDetail(Model model, @RequestParam("problemId") int no) {
		model.addAttribute("problem", problemService.getProblemDetail(no));
		return "views/problem/problemDetail";
	}

	// 결과 페이지, 페이지로 이동 후 아래의 /apiRequest 로 fetch 실행함
	@PostMapping("/problemResult")
	public String problemResult(Model model, @RequestParam("answer") String answer, @RequestParam("no") int no) {

		model.addAttribute("problem", problemService.getProblemDetail(no));
		model.addAttribute("answer", answer);

		return "views/problem/problemResult";
	}

	@GetMapping("/problemWrite")
	public String problemWriteForm() {
		return "views/problem/problemWriteForm";
	}

	@PostMapping("/problemWrite")
	public String problemWriteResult(Problem problem, @AuthenticationPrincipal CustomUserDetails principal) {
		// 권한 체크
		if (!checkAuth(principal))
			return "redirect:/loginForm";
		
		problem.setUserId(principal.getUsername());
		problemService.addProblem(problem);
		return "redirect:/problems/";
	}

	@GetMapping("/problemUpdate")
	public String problemUpdateForm(@RequestParam("no") int no, Model model) {
		model.addAttribute("problem", problemService.getProblemDetail(no));
		return "views/problem/problemUpdateForm";
	}

	@PostMapping("/problemUpdate")
	public String problemUpdateResult(Problem problem, @AuthenticationPrincipal CustomUserDetails principal) {
		// 권한 체크
		if (!checkAuth(principal))
			return "redirect:/loginForm";

		problemService.updateProblem(problem);
		return "redirect:/problems/";
	}

	@GetMapping("/problemDelete")
	public String problemDelete(@RequestParam("no") int no, @AuthenticationPrincipal CustomUserDetails principal) {
		// 권한 체크
		if (!checkAuth(principal))
			return "redirect:/loginForm";

		problemService.deleteProblem(no);
		return "redirect:/problems/";
	}

	// 작성된 답안을 gemini에게 전송하여 결과를 받아옴
	// 이런 메소드가 더 늘어날 시 RestController로 분리 필요
	@PostMapping("/apiRequest")
	@ResponseBody
	public Map<String, String> apiRequest(@RequestBody CodeSubmitRequest request) {
		Map<String, String> map = new HashMap<>();

		GenerateContentResponse response = aiSupportService.apiRequest(request);
		map.put("result", response.text());
		return map;
	}

	// 권한 체크 메소드
	private boolean checkAuth(CustomUserDetails principal) {
		if (principal == null
				|| !principal.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
			return false;
		}
		return true;
	}
}
