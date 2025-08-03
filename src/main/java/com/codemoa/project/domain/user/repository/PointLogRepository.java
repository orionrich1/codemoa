package com.codemoa.project.domain.user.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codemoa.project.domain.user.entity.PointEventType;
import com.codemoa.project.domain.user.entity.PointLog;
import com.codemoa.project.domain.user.entity.User;

public interface PointLogRepository extends JpaRepository<PointLog, Integer> {
    // JpaRepository<PointLog, Integer>
	boolean existsByUserAndEventTypeAndCreatedAtAfter(User user, PointEventType eventType, LocalDateTime dateTime);
}