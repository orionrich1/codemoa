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
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void message(ChatMessageDto message, Principal principal) {
        message.setSentAt(LocalDateTime.now());
        String username = principal.getName();

        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
            chatService.addUserToRoom(message.getRoomId(), username);
            Set<String> users = chatService.getUsersInRoom(message.getRoomId());
            messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId() + "/users", users);

            List<ChatMessage> history = chatService.getMessages(message.getRoomId());
            history.forEach(msg -> {
                ChatMessageDto historyDto = new ChatMessageDto();
                historyDto.setType(msg.getType());
                historyDto.setRoomId(msg.getRoomId());
                historyDto.setSender(msg.getSender());
                historyDto.setMessage(msg.getMessage());
                historyDto.setSentAt(msg.getSentAt());
                messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/messages", historyDto);
            });
            // 입장 메시지는 더 이상 채팅창으로 보내지 않습니다.
            return; 

        } else if (ChatMessage.MessageType.QUIT.equals(message.getType())) {
            chatService.removeUserFromRoom(message.getRoomId(), username);
            Set<String> users = chatService.getUsersInRoom(message.getRoomId());
            messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId() + "/users", users);
            // 퇴장 메시지는 더 이상 채팅창으로 보내지 않습니다.
            return;
        }
        
        chatService.saveMessage(message);
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}