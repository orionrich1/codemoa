package com.springbootstudy.bbs.ajax;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootstudy.bbs.service.BoardService;

@RestController
public class BoardAjaxController {
	
	// 의존객체 주입 설정
	@Autowired
	private BoardService boardService;
	
	// 추천/땡큐에 대한 Ajax 요청을 처리하는 메서드
	@PostMapping("/recommend.ajax")
	public Map<String, Integer> recommend(@RequestParam("no") int no,
			@RequestParam("recommend") String recommend) {
		
		return boardService.recommend(no, recommend);
	}
	

}
