package com.codemoa.project.domain.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomId;
    private String sender; // 메시지 보낸 사람 (닉네임 또는 식별자)

    @Column(columnDefinition = "TEXT")
    private String message;

    private LocalDateTime sentAt;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    public enum MessageType {
        ENTER, TALK, QUIT
    }

    @Builder
    public ChatMessage(MessageType type, String roomId, String sender, String message, LocalDateTime sentAt) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.sentAt = sentAt;
    }
}