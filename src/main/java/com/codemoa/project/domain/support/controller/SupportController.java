package com.codemoa.project.domain.support.controller;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codemoa.project.domain.support.entity.Board;
import com.codemoa.project.domain.support.entity.Reply;
import com.codemoa.project.domain.support.service.SupportService;


@Controller
public class SupportController {
	@Autowired
	private SupportService supportService;
	
	
	///////////////////////////// FAQ ////////////////////////////////
	@GetMapping("/faq/boardDetail")
	public String getBoard(Model model,
			@RequestParam("no") int no,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		
		Board board = supportService.getBoard2(no, true);
		
		model.addAttribute("board", board);
		model.addAttribute("pageNum", pageNum);
		
		List<Reply> replyList = supportService.replyList(no);
		model.addAttribute("replyList", replyList);
		
		return "views/FAQDetail";
	}
	
	@GetMapping({"/faq", "/faq/boardList"})
	public String boardList(Model model,
			@RequestParam(value="pageNum",
			required=false, defaultValue="1") int pageNum) {
		
		// Service 클래스를 이용해 게시글 리스트를 가져온다.
		Map<String, Object> modelMap = supportService.boardList(pageNum);
		
		// 파라미터로 받은 모델 객체에 뷰로 보낼 모델을 저장한다.
		model.addAllAttributes(modelMap);
		
		return "views/FAQ";
	}
	
	///////////////////////// Q&A ///////////////////////////
	@GetMapping("/qna/boardDetail2")
	public String getBoard2(Model model,
			@RequestParam("no") int no,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		
		Board board = supportService.getBoard2(no, true);
		
		model.addAttribute("board", board);
		model.addAttribute("pageNum", pageNum);
		
		return "views/Q&ADetail";
	}
	
	@GetMapping({"/qna/boardList2"})
	public String boardList2(Model model,
			@RequestParam(value="pageNum",
			required=false, defaultValue="1") int pageNum) {
		
		// Service 클래스를 이용해 게시글 리스트를 가져온다.
		Map<String, Object> modelMap = supportService.boardList2(pageNum);
		
		// 파라미터로 받은 모델 객체에 뷰로 보낼 모델을 저장한다.
		model.addAllAttributes(modelMap);
		
		return "views/Q&A";
	}
}
