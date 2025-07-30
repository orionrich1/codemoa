package com.codemoa.project.domain.community.dto.response;


import com.codemoa.project.domain.community.entity.CommunityBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListResponse {

    private final Integer boardNo;
    private final String title;
    private final String category;
    private final String authorNickname;
    private final LocalDateTime createdAt;

    // 엔티티를 DTO로 변환하는 생성자
    public BoardListResponse(CommunityBoard board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.category = board.getCategory();
        this.authorNickname = board.getUser().getNickname();
        this.createdAt = board.getCreatedAt();
    }
}