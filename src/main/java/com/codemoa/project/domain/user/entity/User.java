package com.codemoa.project.domain.user.entity;

import com.codemoa.project.domain.community.entity.CommunityBoard; // 예시 경로
import com.codemoa.project.domain.community.entity.Comment; // 예시 경로
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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

    @Column(name = "ban_left_day")
    private LocalDateTime banLeftDay;

    @Column(name = "ban_reason")
    private String banReason;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommunityBoard> communityBoards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    
    @Column(columnDefinition = "INT DEFAULT 0") // DB에 기본값 0 설정
    private int point;


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