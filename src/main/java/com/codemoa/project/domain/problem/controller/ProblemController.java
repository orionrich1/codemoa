//도영
package com.codemoa.project.domain.problem.controller;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codemoa.project.domain.problem.dto.request.UpdateProblemRequest;
import com.codemoa.project.domain.problem.dto.request.WriteProblemRequest;
import com.codemoa.project.domain.problem.service.ProblemService;
import com.codemoa.project.domain.user.security.CustomUserDetails;
import com.codemoa.project.domain.user.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/problems")
@RequiredArgsConstructor
public class ProblemController {

	private final ProblemService problemService;

	// /problems 까지만 입력 시에도 메인 페이지로 빠지게 설정
	@GetMapping
	public String redirect() {
		return "redirect:/problems/";
	}

	// 메인 페이지, 문제 리스트 출력
	@GetMapping("/")
	public String getProblemList(Model model, @AuthenticationPrincipal CustomUserDetails principal) {
		model.addAttribute("problemList", problemService.getProblemList());
		if (principal != null) {
			String userId = principal.getUsername();
			Map<Integer, Integer> bestScores = problemService.getBestScoreMap(userId);
			model.addAttribute("bestScores", bestScores);
			model.addAttribute("solvedCount", problemService.countSolvedProblems(userId));
			model.addAttribute("totalCount", problemService.countProblems());
		}
		return "views/problem/problemList";
	}
	
	@GetMapping("/aiAnswer")
	public String aiAnswer() {
		return "views/problem/aiAnswer";
	}

	// 상세 페이지, 문제 내용 및 답안 입력 화면
	@GetMapping("/problemDetail")
	public String getProblemDetail(Model model, @RequestParam("problemId") int no) {
		model.addAttribute("problem", problemService.getProblemDetail(no));
		return "views/problem/problemDetail";
	}

	// 결과 페이지, 페이지로 이동 후 /apiRequest 로 fetch 실행함
	@PostMapping("/problemResult")
	public String problemResult(Model model, @RequestParam("answer") String answer, @RequestParam("no") int no) {

		model.addAttribute("problem", problemService.getProblemDetail(no));
		model.addAttribute("answer", answer);

		return "views/problem/problemResult";
	}

	// 내 학습 통계 페이지 (기능 7)
	@GetMapping("/stats")
	public String myStats(Model model, @AuthenticationPrincipal CustomUserDetails principal) {
		if (principal == null) return "redirect:/loginForm";
		model.addAttribute("stats", problemService.getMyStats(principal.getUsername()));
		return "views/problem/stats";
	}

	// 내 풀이 이력 페이지 (기능 6)
	@GetMapping("/myHistory")
	public String myHistory(Model model, @AuthenticationPrincipal CustomUserDetails principal) {
		if (principal == null) return "redirect:/loginForm";
		String userId = principal.getUsername();
		model.addAttribute("historyList", problemService.getMyHistory(userId));
		model.addAttribute("solvedCount", problemService.countSolvedProblems(userId));
		model.addAttribute("totalCount", problemService.countProblems());
		return "views/problem/myHistory";
	}

	@GetMapping("/problemWrite")
	public String problemWriteForm() {
		return "views/problem/problemWriteForm";
	}

	@PostMapping("/problemWrite")
	public String problemWriteResult(WriteProblemRequest request,
			@AuthenticationPrincipal CustomUserDetails principal) {
		if (!checkAuth(principal))
			return "redirect:/loginForm";

		int newProblemId = problemService.addProblemAndGetId(request, principal.getUsername());
		return "redirect:/problems/problemDetail?problemId=" + newProblemId;
	}

	@GetMapping("/problemUpdate")
	public String problemUpdateForm(@RequestParam("no") int no, Model model) {
		model.addAttribute("problem", problemService.getProblemDetail(no));
		return "views/problem/problemUpdateForm";
	}

	@PostMapping("/problemUpdate")
	public String problemUpdateResult(UpdateProblemRequest request,
			@AuthenticationPrincipal CustomUserDetails principal) {
		if (!checkAuth(principal))
			return "redirect:/loginForm";

		problemService.updateProblem(request);
		return "redirect:/problems/problemDetail?problemId=" + request.getProblemId();
	}

	@GetMapping("/problemDelete")
	public String problemDelete(@RequestParam("no") int no, @AuthenticationPrincipal CustomUserDetails principal) {
		// 권한 체크
		if (!checkAuth(principal))
			return "redirect:/loginForm";

		problemService.deleteProblem(no);
		return "redirect:/problems/";
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
