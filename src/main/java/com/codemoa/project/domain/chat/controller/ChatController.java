//윤식
package com.codemoa.project.domain.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.codemoa.project.domain.chat.dto.request.ChatMessageRequest;

@Controller
public class ChatController {

    @MessageMapping("/chat/message")
    @SendTo("/sub/chat/room/1")
    // 2. 파라미터 타입을 ChatMessage 엔티티에서 ChatMessageRequest DTO로 변경합니다.
    //    반환 타입도 동일하게 DTO로 변경해줍니다.
    public ChatMessageRequest message(ChatMessageRequest message) {
        // 받은 DTO를 그대로 다시 클라이언트들에게 보내줍니다.
        return message;
    }
}