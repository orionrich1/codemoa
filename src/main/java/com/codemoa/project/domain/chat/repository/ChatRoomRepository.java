package com.codemoa.project.domain.chat.repository;

import com.codemoa.project.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {
    Optional<ChatRoom> findByRoomId(String roomId);
}