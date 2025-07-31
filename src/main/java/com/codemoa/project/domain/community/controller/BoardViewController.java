package com.codemoa.project.domain.community.controller;

import com.codemoa.project.domain.community.dto.response.BoardDetailResponse;
import com.codemoa.project.domain.community.dto.response.BoardListResponse;
import com.codemoa.project.domain.community.service.CommunityBoardService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller // 뷰를 반환할 때는 @RestController가 아닌 @Controller를 사용합니다.
@RequiredArgsConstructor
public class BoardViewController {

    private final CommunityBoardService communityBoardService;

    @GetMapping("/list") // 예: http://localhost:8080/boards
    public String boardList(Model model) {
        // 1. 서비스에서 모든 게시글 데이터를 가져옵니다.
        List<BoardListResponse> boards = communityBoardService.findAll();

        // 2. Model 객체에 "boards"라는 이름으로 데이터를 담습니다.
        model.addAttribute("boards", boards);

        // 3. "boards/list" 라는 이름의 뷰(HTML 파일)를 찾아서 반환합니다.
        return "views/community/boardList"; 
    }
    
    
    @GetMapping("/boards/{boardNo}")
    public String boardDetail(@PathVariable("boardNo") Integer boardNo, Model model) {
        // 1. 서비스에서 특정 ID의 게시글 데이터를 가져옵니다.
        BoardDetailResponse board = communityBoardService.findById(boardNo);

        // 2. Model 객체에 "board"라는 이름으로 데이터를 담습니다.
        model.addAttribute("board", board);

        // 3. "boards/detail" 라는 이름의 뷰(HTML 파일)를 찾아서 반환합니다.
        return "views/community/boardDetail";
    }
    
    @GetMapping("/boards/write")
	public String boardWrite() {
		// 게시글 작성 페이지로 이동
		return "views/community/boardWrite"; // "views/community/boardWrite.html" 파일을 찾아서 반환합니다.
	}
    
    /**
     * 게시글 수정 페이지를 보여주는 메서드
     */
    @GetMapping("/boards/{boardNo}/edit")
    public String boardEditForm(@PathVariable("boardNo") Integer boardNo, Model model) {
        BoardDetailResponse board = communityBoardService.findById(boardNo);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();
        if (!board.getAuthorId().equals(currentUserId)) {
            return "redirect:/boards/" + boardNo; 
        }

        model.addAttribute("board", board);
        
        // 요청하신 대로 "boardUpdate"로 수정했습니다.
        return "views/community/boardUpdate"; 
    }
    
}