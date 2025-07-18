//도영
package com.codemoa.project.domain.problem.controller;

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
import com.codemoa.project.domain.problem.service.ProblemService;

import com.google.genai.types.GenerateContentResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/problems")
@RequiredArgsConstructor
public class ProblemController {

	private final ProblemService problemService;

	@GetMapping("/")
	public String getProblemList(Model model) {
		model.addAttribute("problemList", problemService.getProblemList());
		return "views/problem/problemList";
	}

	@GetMapping("/problemDetail")
	public String getProblemDetail(Model model, @RequestParam("problemId") int no) {
		model.addAttribute("problem", problemService.getProblemDetail(no));
		return "views/problem/problemDetail";
	}

	@PostMapping("/problemResult")
	public String problemResult(Model model, @RequestParam("answer") String answer, @RequestParam("no") int no) {
		Problem problem = problemService.getProblemDetail(no);

		model.addAttribute("problem", problem);
		model.addAttribute("answer", answer);

		return "views/problem/problemResult";
	}

	@PostMapping("/apiRequest")
	@ResponseBody
	public Map<String, String> apiRequest(@RequestBody CodeSubmitRequest request) {
		Map<String, String> map = new HashMap<>();
		
		GenerateContentResponse response = problemService.apiRequest(request);
		map.put("result", response.text());
		return map;
	}
}
