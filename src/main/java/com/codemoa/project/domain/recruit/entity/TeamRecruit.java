//종효
package com.codemoa.project.domain.recruit.entity;

import java.time.LocalDateTime;

public class TeamRecruit {

    private int recruitId;      // recruit_id (PK)
    private String userId;      // user_id (FK, 작성자)
    private String title;
    private String content;
    private String category;    // 모집 분야 (예: 공모전, 스터디)
    private String status;      // 모집 상태 (예: 모집중, 모집완료)
    private LocalDateTime dueDate;     // 모집 마감일
    private int viewCount;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    // --- Getters and Setters ---

    public int getRecruitId() { return recruitId; }
    public void setRecruitId(int recruitId) { this.recruitId = recruitId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }

    public LocalDateTime getRegDate() { return regDate; }
    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }

    public LocalDateTime getUpdateDate() { return updateDate; }
    public void setUpdateDate(LocalDateTime updateDate) { this.updateDate = updateDate; }
}