// CommunityBoardRepository.java
package com.codemoa.project.domain.community.repository;

import com.codemoa.project.domain.community.entity.CommunityBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommunityBoardRepository extends JpaRepository<CommunityBoard, Integer>, JpaSpecificationExecutor<CommunityBoard> {

    /**
     * 댓글 수(참여) 우선, 동률 시 추천수·최신 글번호로 정렬한 인기 글.
     * 조회수 컬럼이 없어 문서의 ‘인기’ 축을 댓글·추천으로 대체합니다.
     */
    @Query(value = """
            SELECT b.* FROM community_board b
            LEFT JOIN (
                SELECT board_no, COUNT(*) AS cnt FROM `comment` GROUP BY board_no
            ) c ON b.board_no = c.board_no
            ORDER BY COALESCE(c.cnt, 0) DESC, COALESCE(b.recommend, 0) DESC, b.board_no DESC
            LIMIT :limit
            """, nativeQuery = true)
    List<CommunityBoard> findPopularByEngagement(@Param("limit") int limit);
}
