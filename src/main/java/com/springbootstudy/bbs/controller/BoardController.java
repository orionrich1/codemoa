package com.springbootstudy.bbs.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootstudy.bbs.domain.Board;
import com.springbootstudy.bbs.domain.Reply;
import com.springbootstudy.bbs.service.BoardService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	
	///////////////////////////// FAQ ////////////////////////////////
	@GetMapping("/boardDetail")
	public String getBoard(Model model,
			@RequestParam("no") int no,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		
		Board board = boardService.getBoard(no, true);
		
		model.addAttribute("board", board);
		model.addAttribute("pageNum", pageNum);
		
		return "views/FAQDetail";
	}
	
	@GetMapping({"/", "/boardList"})
	public String boardList(Model model,
			@RequestParam(value="pageNum",
			required=false, defaultValue="1") int pageNum) {
		
		// Service 클래스를 이용해 게시글 리스트를 가져온다.
		Map<String, Object> modelMap = boardService.boardList(pageNum);
		
		// 파라미터로 받은 모델 객체에 뷰로 보낼 모델을 저장한다.
		model.addAllAttributes(modelMap);
		
		return "views/FAQ";
	}
	
	///////////////////////// Q&A ///////////////////////////
	@GetMapping("/boardDetail2")
	public String getBoard2(Model model,
			@RequestParam("no") int no,
			@RequestParam(value="pageNum", defaultValue="1") int pageNum) {
		
		Board board = boardService.getBoard2(no, true);
		
		model.addAttribute("board", board);
		model.addAttribute("pageNum", pageNum);
		
		List<Reply> replyList = boardService.replyList(no);
		model.addAttribute("replyList", replyList);
		
		return "views/Q&ADetail";
	}
	
	@GetMapping({"/boardList2"})
	public String boardList2(Model model,
			@RequestParam(value="pageNum",
			required=false, defaultValue="1") int pageNum) {
		
		// Service 클래스를 이용해 게시글 리스트를 가져온다.
		Map<String, Object> modelMap = boardService.boardList2(pageNum);
		
		// 파라미터로 받은 모델 객체에 뷰로 보낼 모델을 저장한다.
		model.addAllAttributes(modelMap);
		
		return "views/Q&A";
	}
	
	@GetMapping("/addBoard")
	public String addBoard() {
		return "views/writeForm";
	}
	
	@PostMapping("/addBoard")
	public String addBoard(Board board) {
		boardService.addBoard(board);
		
		return "redirect:boardList2";
	}
	
	@PostMapping("/updateForm")
	public String updateBoard(Model model,
			HttpServletResponse response, PrintWriter out,
			@RequestParam("no") int no, @RequestParam("pass") String pass) {
		boolean isPassCheck = boardService.isPassCheck(no, pass);
		if(! isPassCheck) {
			response.setContentType("text/html; charset=utf-8");
			out.println("<script>");
			out.println(" alert('비밀번호가 맞지 않습니다.');");
			out.println(" history.back();");
			out.println("</script>");
			
			return null;
		}
		
		Board board = boardService.getBoard(no, false);
		model.addAttribute("board", board);
		return "views/updateForm";
	}
	
	@PostMapping("/update")
	public String updateBoard(Board board,
	HttpServletResponse response, PrintWriter out) {

		boolean isPassCheck = boardService.isPassCheck(board.getNo(), board.getPass());
		if(! isPassCheck) {
		response.setContentType("text/html; charset=utf-8");
		out.println("<script>");
		out.println(" alert('비밀번호가 맞지 않습니다.');");
		out.println(" history.back();");
		out.println("</script>");

		return null;
		}
		
		boardService.updateBoard(board);
		return "redirect:boardList2";
		
		}
	
	@PostMapping("/delete")
	public String deleteBoard(
	HttpServletResponse response, PrintWriter out,
	@RequestParam("no") int no, @RequestParam("pass") String pass) {

	boolean isPassCheck = boardService.isPassCheck(no, pass);
	if(! isPassCheck) {
	response.setContentType("text/html; charset=utf-8");
	out.println("<script>");
	out.println(" alert('비밀번호가 맞지 않습니다.');");
	out.println(" history.back();");
	out.println("</script>");
	return null;
	}

	boardService.deleteBoard(no);
	return "redirect:boardList2";
	}
	
}
