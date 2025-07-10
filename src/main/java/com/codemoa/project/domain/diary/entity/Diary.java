//도영
package com.codemoa.project.domain.diary.entity;

import java.time.LocalDateTime;

public class Diary {

    private int diaryId;        // diary_id (PK)
    private String userId;      // user_id (FK)
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    // --- Getters and Setters ---

    public int getDiaryId() { return diaryId; }
    public void setDiaryId(int diaryId) { this.diaryId = diaryId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getRegDate() { return regDate; }
    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }

    public LocalDateTime getUpdateDate() { return updateDate; }
    public void setUpdateDate(LocalDateTime updateDate) { this.updateDate = updateDate; }
}