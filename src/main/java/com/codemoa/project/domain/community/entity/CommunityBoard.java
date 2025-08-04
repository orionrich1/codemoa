package com.codemoa.project.domain.community.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import com.codemoa.project.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "community_board")
public class CommunityBoard {

    public enum PostType {
        NORMAL, QUESTION
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no")
    private Integer boardNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    private Integer recommend;

    private String file1;

    @Column(nullable = false)
    private String category;

    // ▼▼▼ [수정됨] @Column에 name 속성 추가 ▼▼▼
    @Enumerated(EnumType.STRING)
    @Column(name = "post_type", nullable = false)
    private PostType postType;

    @Column(name = "staked_points")
    private Integer stakedPoints;

    @Column(name = "is_resolved")
    private boolean isResolved;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public CommunityBoard(User user, String title, String content, int recommend, String file1,
                          String category, PostType postType, Integer stakedPoints, boolean isResolved) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.recommend = recommend;
        this.file1 = file1;
        this.category = category;
        this.postType = postType;
        this.stakedPoints = stakedPoints;
        this.isResolved = isResolved;
    }
    
    public static CommunityBoard create(User user, String title, String content, String category, PostType postType, Integer stakedPoints) {
        CommunityBoard board = new CommunityBoard();
        board.user = user;
        board.title = title;
        board.content = content;
        board.category = category;
        board.postType = postType;
        board.stakedPoints = (stakedPoints == null) ? 0 : stakedPoints;
        board.isResolved = false;
        board.recommend = 0;
        return board;
    }

    public void update(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
    
    public void resolve() {
        this.isResolved = true;
    }
}