package com.codemoa.project.domain.user.entity;

import com.codemoa.project.domain.community.entity.CommunityBoard;
import com.codemoa.project.domain.community.entity.Comment;
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
    @Column(name = "user_id", nullable = false, length = 10)
    private String userId;

    @Column(name = "name", length = 10)
    private String name;

    @Column(name = "nickname", unique = true, length = 10)
    private String nickname;

    @Column(name = "mobile", length = 15)
    private String mobile;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "total_points", nullable = false)
    private Integer totalPoints = 0;

    @Column(name = "membership_date", nullable = false)
    private LocalDateTime membershipDate;

    @Column(name = "unban_date", nullable = false)
    private LocalDateTime unbanDate;

    // ▼▼▼▼▼ [수정된 부분 시작] ▼▼▼▼▼
    @Enumerated(EnumType.STRING) // DB에 Enum 이름을 문자열로 저장
    @Column(name = "grade", nullable = false)
    private UserGrade grade;
    // ▲▲▲▲▲ [수정된 부분 끝] ▲▲▲▲▲
    
    @Column(name = "user_position")
    private String userPosition;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommunityBoard> communityBoards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // 생성자에서 grade 초기화
    public User(String userId, String name, String nickname, String email, String mobile, UserGrade grade) {
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.mobile = mobile;
        this.totalPoints = 0;
        this.grade = grade; // 필드명 변경에 따른 수정
    }

    @PrePersist
    public void prePersist() {
        this.membershipDate = LocalDateTime.now();
        this.unbanDate = LocalDateTime.now();
    }
    
    public void addPoints(int points) {
        if (this.totalPoints == null) {
            this.totalPoints = 0;
        }
        this.totalPoints += points;
    }
}