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
    private final String authorId;
    private final String authorNickname;
    private final LocalDateTime createdAt;
    private final List<CommentResponse> comments;
    private final Integer stakedPoints;
    private final boolean isResolved; 
    private final String authorGradeIconName;

    // 생성자를 수정하여 댓글 목록을 받도록 합니다.
    public BoardDetailResponse(CommunityBoard board, List<CommentResponse> comments) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.category = board.getCategory();
        this.authorNickname = board.getUser().getNickname();
        this.authorId = board.getUser().getUserId();
        this.authorGradeIconName = board.getUser().getGrade().name().toLowerCase();
        this.createdAt = board.getCreatedAt();
        this.comments = comments;
        this.stakedPoints = board.getStakedPoints();
        this.isResolved = board.isResolved(); 
    }
}