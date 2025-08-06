package com.codemoa.project.domain.chat.repository;

import com.codemoa.project.domain.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime; // 1. import 추가
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByRoomIdOrderBySentAtAsc(String roomId);

    // ▼▼▼ [핵심 추가 부분] ▼▼▼
    /**
     * 특정 시간 이전에 생성된 메시지를 모두 삭제합니다.
     * @param dateTime 기준 시간
     */
    void deleteBySentAtBefore(LocalDateTime dateTime);
    // ▲▲▲ [핵심 추가 부분] ▲▲▲
}