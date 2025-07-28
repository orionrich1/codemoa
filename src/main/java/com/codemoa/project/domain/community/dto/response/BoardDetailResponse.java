package com.codemoa.project.domain.community.dto.response;

import com.codemoa.project.domain.community.entity.CommunityBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardDetailResponse {

    private final Integer boardNo;
    private final String title;
    private final String content;
    private final String category;
    private final String authorNickname; // 작성자 닉네임
    private final LocalDateTime createdAt;

    // 엔티티를 DTO로 변환하는 생성자
    public BoardDetailResponse(CommunityBoard board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.category = board.getCategory();
        this.authorNickname = board.getUser().getNickname(); // User 엔티티에서 닉네임 가져오기
        this.createdAt = board.getCreatedAt();
    }
}