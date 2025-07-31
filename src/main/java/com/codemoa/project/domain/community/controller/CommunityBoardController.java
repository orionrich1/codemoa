package com.codemoa.project.domain.community.controller;


import com.codemoa.project.domain.community.dto.request.CreateBoardRequest;
import com.codemoa.project.domain.community.dto.request.UpdateBoardRequest;
import com.codemoa.project.domain.community.dto.response.BoardDetailResponse;
import com.codemoa.project.domain.community.dto.response.BoardListResponse;
import com.codemoa.project.domain.community.service.CommunityBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class CommunityBoardController {

    private final CommunityBoardService communityBoardService;

    /**
     * 게시글 생성
     */
    @PostMapping
    public ResponseEntity<Void> createBoard(@RequestBody CreateBoardRequest request, Authentication authentication) {
        String currentUserId = authentication.getName();
        communityBoardService.create(request, currentUserId);
        return ResponseEntity.ok().build();
    }

    /**
     * 게시글 단건 조회
     */
    @GetMapping("/{boardNo}")
    public ResponseEntity<BoardDetailResponse> getBoard(@PathVariable("boardNo") Integer boardNo) {
        BoardDetailResponse response = communityBoardService.findById(boardNo);
        return ResponseEntity.ok(response);
    }

    /**
     * 게시글 전체 목록 조회
     */
    @GetMapping
    public ResponseEntity<List<BoardListResponse>> getBoardList() {
        List<BoardListResponse> response = communityBoardService.findAll();
        return ResponseEntity.ok(response);
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/{boardNo}")
    public ResponseEntity<Void> updateBoard(@PathVariable("boardNo") Integer boardNo, @RequestBody UpdateBoardRequest request, Authentication authentication) {
        String currentUserId = authentication.getName();
        communityBoardService.update(boardNo, request, currentUserId);
        return ResponseEntity.ok().build();
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("boardNo") Integer boardNo, Authentication authentication) {
        String currentUserId = authentication.getName();
        communityBoardService.delete(boardNo, currentUserId);
        return ResponseEntity.ok().build();
    }
}