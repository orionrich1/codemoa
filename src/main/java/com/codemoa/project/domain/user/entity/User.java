package com.codemoa.project.domain.user.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
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

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "total_points")
    private Integer totalPoints;
    
    @Column(name = "unban_date")
    private LocalDateTime unbanDate;

    @Column(name = "membership_date")
    private LocalDateTime membershipDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grade_id")
    private UserGrade userGrade;
    
    @Column(name = "ban_left_day") // DB 컬럼명은 ban_left_day로 지정됩니다.
    private LocalDateTime banLeftDay;
    
    @Column(name = "ban_reason")
    private String banReason;

    public User(String userId, String name, String nickname, String email, String mobile, Integer totalPoints, UserGrade userGrade) {
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.mobile = mobile;
        this.totalPoints = totalPoints;
        this.userGrade = userGrade;
    }

    @PrePersist
    public void prePersist() {
    	this.membershipDate = LocalDateTime.now();
    	this.unbanDate = LocalDateTime.now();
    }
}