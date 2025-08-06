package com.codemoa.project.domain.chat.service;

import com.codemoa.project.domain.chat.dto.ChatMessageDto;
import com.codemoa.project.domain.chat.dto.ChatRoomDto;
import com.codemoa.project.domain.chat.entity.ChatMessage;
import com.codemoa.project.domain.chat.entity.ChatRoom;
import com.codemoa.project.domain.chat.repository.ChatMessageRepository;
import com.codemoa.project.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    // ▼▼▼ [접속자 관리 Map 추가] ▼▼▼
    private final Map<String, Set<String>> roomUsers = new ConcurrentHashMap<>();
    // ▲▲▲ [접속자 관리 Map 추가] ▲▲▲

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

    // ▼▼▼ [접속자 관리 메서드 추가] ▼▼▼
    public void addUserToRoom(String roomId, String username) {
        roomUsers.computeIfAbsent(roomId, k -> ConcurrentHashMap.newKeySet()).add(username);
    }

    public void removeUserFromRoom(String roomId, String username) {
        if (roomUsers.containsKey(roomId)) {
            roomUsers.get(roomId).remove(username);
            if (roomUsers.get(roomId).isEmpty()) {
                roomUsers.remove(roomId);
            }
        }
    }

    public Set<String> getUsersInRoom(String roomId) {
        return roomUsers.getOrDefault(roomId, Collections.emptySet());
    }
    // ▲▲▲ [접속자 관리 메서드 추가] ▲▲▲

    @Scheduled(cron = "0 0 * * * *")
    @Transactional
    public void cleanupOldMessages() {
        LocalDateTime sixHoursAgo = LocalDateTime.now().minusHours(6);
        chatMessageRepository.deleteBySentAtBefore(sixHoursAgo);
        System.out.println("오래된 채팅 메시지 삭제 완료 (6시간 이전 데이터) - 실행 시각: " + LocalDateTime.now());
    }
}