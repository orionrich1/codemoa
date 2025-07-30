//윤식
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
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 무분별한 객체 생성 방지
@Table(name = "community_board") // DB 네이밍 컨벤션은 보통 snake_case를 사용합니다.
public class CommunityBoard { // 클래스 이름도 통일하면 좋습니다.

    public enum PostType {
        NORMAL, QUESTION
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1. ID 자동 생성 전략 추가
    @Column(name = "board_no") // board 번호 
    private Integer boardNo;

    @ManyToOne(fetch = FetchType.LAZY) // 2. User 엔티티와 관계 매핑
    @JoinColumn(name = "user_id") // DB의 FK 컬럼
    private User user;

    @Column(nullable = false)
    private String title;

    @Lob // 텍스트가 길어질 수 있으므로 @Lob으로 지정
    @Column(nullable = false)
    private String content;

    private Integer recommend;

    private String file1;

    @Column(nullable = false)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostType postType;

    @Column(name = "staked_points")
    private Integer stakedPoints; // 3. 타입 Integer로 변경

    @Column(name = "is_adopted")
    private boolean isAdopted; // 3. 타입 boolean으로 변경

    @CreationTimestamp // 생성 시 자동으로 현재 시간이 들어감
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // 빌더 패턴을 사용하면 객체 생성이 유연하고 안전해집니다.
    @Builder
    public CommunityBoard(User user, String title, String content, int recommend, String file1,
                          String category, PostType postType, Integer stakedPoints, boolean isAdopted) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.recommend = recommend;
        this.file1 = file1;
        this.category = category;
        this.postType = postType;
        this.stakedPoints = stakedPoints;
        this.isAdopted = isAdopted;
    }
    
    
 // ==================== 생성 로직 (정적 팩토리 메서드) ====================
    /**
     * 게시글 생성을 위한 정적 팩토리 메서드.
     * @Builder 대신 이 메서드를 사용하여 객체를 생성합니다.
     */
    public static CommunityBoard create(User user, String title, String content, String category, PostType postType, Integer stakedPoints) {
        CommunityBoard board = new CommunityBoard();
        board.user = user;
        board.title = title;
        board.content = content;
        board.category = category;
        board.postType = postType;
        board.stakedPoints = (stakedPoints == null) ? 0 : stakedPoints;
        board.isAdopted = false;
        board.recommend = 0;
        // file1은 필요 시 파라미터로 추가 가능
        return board;
    }

    // ==================== 수정 로직 (인스턴스 메서드) ====================
    /**
     * 게시글 수정
     */
    public void update(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}