//도영
package com.codemoa.project.domain.chat.entity;

import java.time.LocalDateTime;

public class ChatMessage {

    private int chatId;         // chat_id (PK)
    private String roomId;      // 채팅방을 구분하기 위한 ID
    private String userId;      // user_id (FK, 메시지 보낸 사람)
    private String message;     // 메시지 내용
    private LocalDateTime regDate;

    // --- Getters and Setters ---

    public int getChatId() { return chatId; }
    public void setChatId(int chatId) { this.chatId = chatId; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getRegDate() { return regDate; }
    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }
}