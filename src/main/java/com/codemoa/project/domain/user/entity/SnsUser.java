package com.codemoa.project.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "snsUser")
public class SnsUser {

    @Id
    @Column(name = "user_id")
    private String userId;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "sns_type", nullable = false)
    private String snsType;

    @Column(name = "sns_id", nullable = false)
    private String snsId;
}