//도영
package com.codemoa.project.domain.problem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codemoa.project.domain.problem.dto.request.CodeSubmitRequest;
import com.codemoa.project.domain.problem.dto.request.SearchListRequest;
import com.codemoa.project.domain.problem.entity.Problem;
import com.codemoa.project.domain.problem.service.AiSupportService;
import com.codemoa.project.domain.problem.service.ProblemService;

import com.google.genai.types.GenerateContentResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/problems")
@RequiredArgsConstructor
public class ProblemRestController {

	private final ProblemService problemService;
	private final AiSupportService aiSupportService;

	// 검색 필터 체크로 인한 리스트 업데이트
	@PostMapping("/listUpdate")
	public Map<String, List<Problem>> getFilterList(@RequestBody SearchListRequest request) {
		Map<String, List<Problem>> map = new HashMap<>();
		map.put("result", problemService.searchProblemList(request));
		return map;
	}

	// 작성된 답안을 gemini에게 전송하여 결과를 받아옴
	@PostMapping("/apiRequest")
	public Map<String, String> apiRequest(@RequestBody CodeSubmitRequest request) {
		Map<String, String> map = new HashMap<>();

		GenerateContentResponse response = aiSupportService.apiRequest(request);
		map.put("result", response.text());
		return map;
	}
	
	// 질문을 gemini에게 전송하여 결과를 받아옴
	@PostMapping("/apiQuestion")
	public Map<String, String> apiQuestion(@RequestBody String question) {
		Map<String, String> map = new HashMap<>();
		GenerateContentResponse response = aiSupportService.apiQuestion(question);
		map.put("result", response.text());
		return map;
	}
}
