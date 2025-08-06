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
    private final String authorGradeIconName; 
    private final LocalDateTime createdAt;
    private final Integer stakedPoints;

    public BoardListResponse(CommunityBoard board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.category = board.getCategory();
        this.authorNickname = board.getUser().getNickname();
        this.authorGradeIconName = board.getUser().getGrade().name().toLowerCase();
        this.createdAt = board.getCreatedAt();
        this.stakedPoints = board.getStakedPoints();
    }
}