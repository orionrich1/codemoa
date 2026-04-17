package com.codemoa.project.domain.community.dto.response;


import com.codemoa.project.domain.community.entity.CommunityBoard;
import com.codemoa.project.common.community.CommunityCategoryPaths;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class BoardListResponse {
    /** URL·API용 글로벌 PK */
    private final Integer boardNo;
    /** 목록 「번호」열: 같은 필터(게시판·검색) 안에서 오래된 글부터 1, 2, 3… */
    private final int categoryPostNo;
    private final String title;
    private final String category;
    private final String categoryUrlSegment;
    private final String authorNickname;
    private final String authorGradeIconName; 
    private final LocalDateTime createdAt;
    private final Integer stakedPoints;

    public BoardListResponse(CommunityBoard board, int categoryPostNo) {
        this.boardNo = board.getBoardNo();
        this.categoryPostNo = categoryPostNo;
        this.title = board.getTitle();
        this.category = board.getCategory();
        this.categoryUrlSegment = CommunityCategoryPaths.toUrlSegment(board.getCategory());
        this.authorNickname = board.getUser().getNickname();
        this.authorGradeIconName = board.getUser().getGrade().name().toLowerCase();
        this.createdAt = board.getCreatedAt();
        this.stakedPoints = board.getStakedPoints();
    }
}