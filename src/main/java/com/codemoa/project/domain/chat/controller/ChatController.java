package com.codemoa.project.domain.chat.controller;

import com.codemoa.project.domain.chat.dto.ChatMessageDto;
import com.codemoa.project.domain.chat.entity.ChatMessage;
import com.codemoa.project.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message) {
        message.setSentAt(LocalDateTime.now());

        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            // 입장 메시지 처리
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");

            // 이전 대화 기록을 새로 입장한 사용자에게만 전송
            List<ChatMessage> history = chatService.getMessages(message.getRoomId());
            // (구현 선택) history를 특정 사용자에게만 보내는 로직 추가 가능
            
        } else if (ChatMessage.MessageType.QUIT.equals(message.getType())) {
            // 퇴장 메시지 처리
            message.setMessage(message.getSender() + "님이 퇴장하셨습니다.");
        }
        
        // 메시지 저장
        chatService.saveMessage(message);
        
        // 해당 채팅방을 구독하고 있는 클라이언트에게 메시지 전송
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}