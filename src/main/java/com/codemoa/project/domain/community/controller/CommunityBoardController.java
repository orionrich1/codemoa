// CommunityBoardController.java
package com.codemoa.project.domain.community.controller;

import com.codemoa.project.domain.community.dto.request.CreateBoardRequest;
import com.codemoa.project.domain.community.dto.request.UpdateBoardRequest;
import com.codemoa.project.domain.community.dto.response.BoardDetailResponse;
import com.codemoa.project.domain.community.dto.response.BoardListResponse;
import com.codemoa.project.domain.community.service.CommunityBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
     * 게시글 전체 목록 페이징 조회 (API)
     * [수정] 서비스의 findAll 메서드에 맞게 검색/필터링 파라미터를 추가했습니다.
     */
    @GetMapping
    public ResponseEntity<Page<BoardListResponse>> getBoardList(
            @RequestParam(value = "category", required = false, defaultValue = "all") String category,
            @RequestParam(value = "searchType", required = false, defaultValue = "title_content") String searchType,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<BoardListResponse> response = communityBoardService.findAll(category, searchType, keyword, pageable);
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
