package com.codemoa.project.domain.chat.controller;

import com.codemoa.project.domain.chat.dto.ChatMessageDto;
import com.codemoa.project.domain.chat.entity.ChatMessage;
import com.codemoa.project.domain.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message, Principal principal) {
        message.setSentAt(LocalDateTime.now());

        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            // 1. 과거 대화 기록을 조회합니다.
            List<ChatMessage> history = chatService.getMessages(message.getRoomId());

            // 2. 조회한 대화 기록을 입장한 사용자에게만 전송합니다.
            history.forEach(msg -> {
                ChatMessageDto historyDto = new ChatMessageDto();
                historyDto.setType(msg.getType());
                historyDto.setRoomId(msg.getRoomId());
                historyDto.setSender(msg.getSender());
                historyDto.setMessage(msg.getMessage());
                historyDto.setSentAt(msg.getSentAt());
                // 개인 큐로 메시지 전송
                messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/messages", historyDto);
            });

            // 3. "입장" 알림은 모두에게 전송합니다.
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
            chatService.saveMessage(message); // 입장 메시지 저장
            messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
            return; 

        } else if (ChatMessage.MessageType.QUIT.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 퇴장하셨습니다.");
        }
        
        // TALK 타입 메시지는 DB에 저장 후 모두에게 전송합니다.
        chatService.saveMessage(message);
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}