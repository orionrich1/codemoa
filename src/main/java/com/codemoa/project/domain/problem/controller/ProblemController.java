//도영
package com.codemoa.project.domain.problem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codemoa.project.domain.problem.service.ProblemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProblemController {

	private final ProblemService problemService;

	@GetMapping("/problems")
	public String getProblemList(Model model) {
		model.addAttribute("problemList", problemService.getProblemList());
		return "views/problem/problemList";
	}
	
	@GetMapping("/problemDetail")
	public String getProblemDetail(Model model, @RequestParam("problemId") int no) {
		model.addAttribute("problem", problemService.getProblemDetail(no));
		return "views/problem/problemDetail";
	}
}
