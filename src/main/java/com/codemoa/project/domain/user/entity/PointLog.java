package com.codemoa.project.domain.user.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "point_log")
public class PointLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_log_id")
    private Integer pointLogId; // 포인트 로그의 고유 ID

    @Column(nullable = false , name = "change_amount")
    private Integer changeAmount; // 변동된 포인트 (+/-)

    @Column(nullable = false)
    private String reason; // 변동 사유 (예: "게시글 작성", "답변 채택")

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 로그 생성 시각

    // --- 연관 관계 ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 이 로그의 주인인 User를 참조
    private User user;

    @Builder
    public PointLog(Integer changeAmount, String reason, User user) {
        this.changeAmount = changeAmount;
        this.reason = reason;
        this.user = user;
    }
}