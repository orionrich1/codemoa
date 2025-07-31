package com.codemoa.project.domain.community.controller;

import com.codemoa.project.domain.community.dto.request.CreateCommentRequest;
import com.codemoa.project.domain.community.entity.Comment;
import com.codemoa.project.domain.community.repository.CommentRepository;
import com.codemoa.project.domain.community.service.CommentService;
import com.codemoa.project.domain.community.service.CommunityBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;
    private final CommunityBoardService communityBoardService;
    private final CommentRepository commentRepository;

    /**
     * 댓글 생성 API
     */
    @PostMapping("/boards/{boardNo}/comments")
    public ResponseEntity<Void> createComment(
            @PathVariable("boardNo") Integer boardNo,
            @RequestBody CreateCommentRequest request,
            Authentication authentication
    ) {
        String currentUserId = authentication.getName();
        commentService.create(boardNo, request.getContent(), currentUserId);
        return ResponseEntity.ok().build();
    }
    
	/**
	 * 댓글 채택 API
	 */
    @PostMapping("/comments/{commentNo}/adopt")
    public ResponseEntity<Void> adoptComment(@PathVariable("commentNo") Integer commentNo, Authentication authentication) {
        String currentUserId = authentication.getName();

        Comment comment = commentRepository.findById(commentNo)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        Integer boardNo = comment.getCommunityBoard().getBoardNo();
        
        communityBoardService.adoptComment(boardNo, commentNo, currentUserId);
        
        return ResponseEntity.ok().build();
    }
}