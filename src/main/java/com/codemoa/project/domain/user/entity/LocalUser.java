package com.codemoa.project.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "local_user") // DB 테이블 이름 "local_user"로 변경
public class LocalUser {

    @Id
    @Column(name = "user_id")
    private String userId;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "pass")
    private String pass;
    
    public LocalUser(User user, String pass) {
        this.user = user;
        this.pass = pass;
    }
}