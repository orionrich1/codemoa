package com.codemoa.project.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "localUser")
public class LocalUser {

    @Id // User의 user_id를 PK로 그대로 사용합니다.
    @Column(name = "user_id")
    private String userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // User의 Id를 LocalUser의 Id로 매핑합니다.
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String pass; // 비밀번호
}