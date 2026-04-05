package com.codemoa.project.support.fixture;

import com.codemoa.project.domain.community.entity.CommunityBoard;
import com.codemoa.project.domain.user.entity.User;

/**
 * CommunityBoard 엔티티 테스트 데이터 팩토리.
 */
public class CommunityBoardFixture {

    public static CommunityBoard create(User user) {
        return CommunityBoard.create(
                user,
                "테스트 게시글 제목",
                "테스트 게시글 내용입니다.",
                "Java",
                CommunityBoard.PostType.NORMAL,
                0
        );
    }

    public static CommunityBoard createQuestion(User user, int stakedPoints) {
        return CommunityBoard.create(
                user,
                "질문입니다: Java 스트림 사용법",
                "Java 스트림 사용법이 궁금합니다.",
                "Java",
                CommunityBoard.PostType.QUESTION,
                stakedPoints
        );
    }

    public static CommunityBoard createWithCategory(User user, String category) {
        return CommunityBoard.create(
                user,
                "[" + category + "] 테스트 게시글",
                "카테고리 테스트 내용",
                category,
                CommunityBoard.PostType.NORMAL,
                0
        );
    }
}
