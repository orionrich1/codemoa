package com.codemoa.project.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int points; // 지급 또는 사용된 포인트

    private String description; // 포인트 변경 사유


    @Enumerated(EnumType.STRING) // Enum의 이름을 DB에 문자열로 저장
    @Column(nullable = false)
    private PointEventType eventType; // 포인트 이벤트 종류

    @CreationTimestamp // @PrePersist 대신 이 어노테이션을 사용합니다.
    @Column(name = "created_at", nullable = false, updatable = false) // updatable = false 추가
    private LocalDateTime createdAt; // 포인트 로그 생성 시각
    
}