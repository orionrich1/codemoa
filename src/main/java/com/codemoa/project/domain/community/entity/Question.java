//윤식
package com.codemoa.project.domain.community.entity;

import java.time.LocalDateTime;

public class Question {

    private int questionId;      // question_id (PK)
    private String userId;       // user_id (FK)
    private String title;
    private String content;
    private int viewCount;
    private int point;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    // --- Getters and Setters ---

    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }

    public LocalDateTime getRegDate() { return regDate; }
    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }

    public LocalDateTime getUpdateDate() { return updateDate; }
    public void setUpdateDate(LocalDateTime updateDate) { this.updateDate = updateDate; }
}