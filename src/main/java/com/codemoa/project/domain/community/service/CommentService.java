package com.codemoa.project.domain.community.service;


import com.codemoa.project.domain.community.dto.response.CommentResponse;
import com.codemoa.project.domain.community.entity.Comment;
import com.codemoa.project.domain.community.entity.CommunityBoard;
import com.codemoa.project.domain.community.repository.CommentRepository;
import com.codemoa.project.domain.community.repository.CommunityBoardRepository;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository; // 댓글 작성자를 찾기 위해 필요
    private final CommunityBoardRepository communityBoardRepository; // 댓글이 달릴 게시글을 찾기 위해 필요

    @Transactional
    public void create(Integer boardNo, String content, String userId) {
        // 1. 사용자(User)와 게시글(CommunityBoard) 엔티티를 조회합니다.
        //    (id가 존재하지 않을 경우 예외 처리를 추가하면 더 좋습니다.)
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        
        CommunityBoard board = communityBoardRepository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 2. Comment 엔티티를 생성합니다.
        Comment comment = Comment.builder()
                .content(content)
                .user(user)
                .communityBoard(board)
                .isAdopted(false) // 기본값은 '채택 안됨'
                .build();

        // 3. 생성된 Comment를 저장합니다.
        commentRepository.save(comment);
    }
    
    @Transactional(readOnly = true) // 조회 전용이므로 readOnly = true
    public List<CommentResponse> findAll(Integer boardNo) {
        // 1. 게시글 번호로 모든 댓글 엔티티를 조회합니다.
        List<Comment> comments = commentRepository.findAllByCommunityBoard_BoardNo(boardNo);
        
        // 2. 조회된 엔티티 리스트를 CommentResponse DTO 리스트로 변환하여 반환합니다.
        return comments.stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }
}
