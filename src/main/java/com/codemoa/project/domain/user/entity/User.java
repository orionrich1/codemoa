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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grade_id", referencedColumnName = "grade_id", foreignKey = @ForeignKey(name = "fk_user_grade"))
    private UserGrade userGrade;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommunityBoard> communityBoards = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // 생성자에서 totalPoints 초기화
    public User(String userId, String name, String nickname, String email, String mobile, UserGrade userGrade) {
        this.userId = userId;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.mobile = mobile;
        this.totalPoints = 0; // 새로 가입 시 포인트는 0으로 시작
        this.userGrade = userGrade;
    }

    @PrePersist
    public void prePersist() {
        this.membershipDate = LocalDateTime.now();
        this.unbanDate = LocalDateTime.now();
    }
    
    // ▼▼▼▼▼ [신규 추가된 메서드] ▼▼▼▼▼
    /**
     * 사용자에게 포인트를 더해주는 편의 메서드
     * @param points 추가할 포인트
     */
    public void addPoints(int points) {
        if (this.totalPoints == null) {
            this.totalPoints = 0;
        }
        this.totalPoints += points;
    }
    // ▲▲▲▲▲ [신규 추가된 메서드] ▲▲▲▲▲
}