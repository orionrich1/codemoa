//윤식
package com.codemoa.project.domain.community.entity;

import java.time.LocalDateTime;

public class FreeBoard {

    private int freeBoardId;    // free_board_id (PK)
    private String userId;      // user_id (FK)
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    // --- Getters and Setters ---

    public int getFreeBoardId() { return freeBoardId; }
    public void setFreeBoardId(int freeBoardId) { this.freeBoardId = freeBoardId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }



    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }

    public LocalDateTime getRegDate() { return regDate; }
    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }

    public LocalDateTime getUpdateDate() { return updateDate; }
    public void setUpdateDate(LocalDateTime updateDate) { this.updateDate = updateDate; }
}