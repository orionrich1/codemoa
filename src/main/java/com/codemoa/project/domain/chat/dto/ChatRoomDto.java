package com.codemoa.project.domain.chat.dto;

import com.codemoa.project.domain.chat.entity.ChatRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatRoomDto {
    private String roomId;
    private String name;

    public ChatRoomDto(ChatRoom entity) {
        this.roomId = entity.getRoomId();
        this.name = entity.getName();
    }
}