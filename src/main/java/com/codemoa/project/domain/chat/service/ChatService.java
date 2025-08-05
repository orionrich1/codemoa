package com.codemoa.project.domain.chat.service;

import com.codemoa.project.domain.chat.dto.ChatMessageDto;
import com.codemoa.project.domain.chat.dto.ChatRoomDto;
import com.codemoa.project.domain.chat.entity.ChatMessage;
import com.codemoa.project.domain.chat.entity.ChatRoom;
import com.codemoa.project.domain.chat.repository.ChatMessageRepository;
import com.codemoa.project.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional(readOnly = true)
    public List<ChatRoomDto> findAllRooms() {
        return chatRoomRepository.findAll().stream()
                .map(ChatRoomDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ChatRoomDto findRoomById(String roomId) {
        return chatRoomRepository.findByRoomId(roomId)
                .map(ChatRoomDto::new)
                .orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다."));
    }

    @Transactional
    public ChatRoomDto createRoom(String name) {
        ChatRoom newRoom = ChatRoom.builder()
                .name(name)
                .build();
        chatRoomRepository.save(newRoom);
        return new ChatRoomDto(newRoom);
    }

    @Transactional
    public void saveMessage(ChatMessageDto messageDto) {
        ChatMessage message = ChatMessage.builder()
                .type(messageDto.getType())
                .roomId(messageDto.getRoomId())
                .sender(messageDto.getSender())
                .message(messageDto.getMessage())
                .sentAt(LocalDateTime.now())
                .build();
        chatMessageRepository.save(message);
    }
    
    @Transactional(readOnly = true)
    public List<ChatMessage> getMessages(String roomId) {
        return chatMessageRepository.findByRoomIdOrderBySentAtAsc(roomId);
    }
}