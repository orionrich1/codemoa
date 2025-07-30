package com.codemoa.project.domain.community.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codemoa.project.domain.community.dto.request.CreateCommentRequest;
import com.codemoa.project.domain.community.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api") // API 경로의 공통 부분
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 생성 API
     * @param boardNo 댓글을 작성할 게시글의 번호
     * @param request 댓글 내용(content)을 담은 DTO
     * @return 성공 응답 (200 OK)
     */
    @PostMapping("/boards/{boardNo}/comments")
    public ResponseEntity<Void> createComment(
            @PathVariable Integer boardNo,
            @RequestBody CreateCommentRequest request
    ) {
        // TODO: 로그인 기능 구현 후, 실제 사용자 ID를 가져와야 합니다.
        String currentUserId = "oaeoae12"; // 임시 사용자 ID

        commentService.create(boardNo, request.getContent(), currentUserId);

        return ResponseEntity.ok().build();
    }
}