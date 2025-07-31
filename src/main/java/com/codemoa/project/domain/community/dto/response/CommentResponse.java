package com.codemoa.project.domain.community.dto.response;

import com.codemoa.project.domain.community.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private final Integer commentNo;
    private final String content;
    private final String authorNickname;
    private final LocalDateTime createdAt;
    private final boolean isAdopted;

    public CommentResponse(Comment comment) {
    	this.commentNo = comment.getCommentNo();
        this.content = comment.getContent();
        this.authorNickname = comment.getUser().getNickname();
        this.createdAt = comment.getCreatedAt();
        this.isAdopted = comment.isAdopted();
    }
}