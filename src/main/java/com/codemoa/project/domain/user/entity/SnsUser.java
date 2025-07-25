package com.codemoa.project.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "sns_user")
public class SnsUser {

    @Id
    @Column(name = "user_id")
    private String userId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "sns_type", nullable = false)
    private String snsType;

    @Column(name = "sns_id", nullable = false)
    private String snsId;
    
    public SnsUser(User user, String snsType) {
        this.user = user;
        this.snsType = snsType;
    }
}