//기찬
package com.codemoa.project.domain.user.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String nickname;

    private String mobile;
    private String address;
    private String email;

    @Column(name = "total_points", nullable = false)
    private Integer totalPoints = 0; // 기본값 0 설정

    @Column(name = "membership_date", updatable = false)
    private LocalDateTime membershipDate;

    // --- 관계 매핑 ---
    @ManyToOne(fetch = FetchType.LAZY) // N:1 관계, User가 N, UserGrade가 1
    @JoinColumn(name = "grade_id") // 외래 키(FK)가 있는 쪽이 관계의 주인입니다.
    private UserGrade userGrade;

    // PrePersist: Entity가 저장되기 전에 실행됩니다.
    @PrePersist
    public void prePersist() {
        this.membershipDate = LocalDateTime.now();
    }
}