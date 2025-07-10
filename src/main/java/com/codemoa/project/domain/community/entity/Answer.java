//윤식
package com.codemoa.project.domain.community.entity;

import java.time.LocalDateTime;

public class Answer {

    private int answerId;        // answer_id (PK)
    private int questionId;      // question_id (FK)
    private String userId;       // user_id (FK)
    private String content;
    private boolean isSelected;  // is_selected
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    // --- Getters and Setters ---

    public int getAnswerId() { return answerId; }
    public void setAnswerId(int answerId) { this.answerId = answerId; }

    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }

    public LocalDateTime getRegDate() { return regDate; }
    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }

    public LocalDateTime getUpdateDate() { return updateDate; }
    public void setUpdateDate(LocalDateTime updateDate) { this.updateDate = updateDate; }
}