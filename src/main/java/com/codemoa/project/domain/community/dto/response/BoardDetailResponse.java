package com.codemoa.project.domain.community.dto.response;

import com.codemoa.project.domain.community.entity.CommunityBoard;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardDetailResponse {

    private final Integer boardNo;
    private final String title;
    private final String content;
    private final String category;
    private final String authorNickname; // 작성자 닉네임
    private final LocalDateTime createdAt;
    private final List<CommentResponse> comments; // 댓글 목록 필드

    // 아래 생성자 하나만 남겨주세요.
    public BoardDetailResponse(CommunityBoard board, List<CommentResponse> comments) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.category = board.getCategory();
        this.authorNickname = board.getUser().getNickname();

        this.createdAt = board.getCreatedAt();
        this.comments = comments; // this.comments로 명확하게 지정
    }
}