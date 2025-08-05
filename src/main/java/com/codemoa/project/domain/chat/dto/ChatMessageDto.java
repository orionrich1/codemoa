package com.codemoa.project.domain.chat.dto;

import com.codemoa.project.domain.chat.entity.ChatMessage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageDto {
    private ChatMessage.MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private LocalDateTime sentAt;
}