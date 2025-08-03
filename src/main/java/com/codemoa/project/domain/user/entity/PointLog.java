package com.codemoa.project.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private LocalDateTime createdAt; // 포인트 로그 생성 시각

    @PrePersist // 엔티티가 저장되기 전에 자동으로 실행
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}