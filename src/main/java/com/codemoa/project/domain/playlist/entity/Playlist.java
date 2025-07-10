//도영
package com.codemoa.project.domain.playlist.entity;

import java.time.LocalDateTime;

public class Playlist {

    private int playlistId;     // playlist_id (PK)
    private String userId;      // user_id (FK)
    private String title;
    private String description;
    private LocalDateTime regDate;

    // --- Getters and Setters ---

    public int getPlaylistId() { return playlistId; }
    public void setPlaylistId(int playlistId) { this.playlistId = playlistId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getRegDate() { return regDate; }
    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }
}