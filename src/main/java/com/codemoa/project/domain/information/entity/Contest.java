package com.codemoa.project.domain.information.entity;

import java.time.LocalDateTime;

public class Contest {

    private int contestId;          // contest_id (PK)
    private String title;
    private String content;
    private String host;            // 주최 기관
    private String category;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int viewCount;
    private LocalDateTime regDate;

    // --- Getters and Setters ---

    public int getContestId() { return contestId; }
    public void setContestId(int contestId) { this.contestId = contestId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }

    public LocalDateTime getRegDate() { return regDate; }
    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }
}