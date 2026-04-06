package com.codemoa.project.domain.community.repository;

import com.codemoa.project.domain.community.entity.CommunityBoard;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.repository.UserRepository;
import com.codemoa.project.support.annotation.RepositoryTest;
import com.codemoa.project.support.fixture.CommunityBoardFixture;
import com.codemoa.project.support.fixture.UserFixture;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RepositoryTest
@Transactional
@DisplayName("CommunityBoardRepository 슬라이스 테스트")
class CommunityBoardRepositoryTest {

    @Autowired
    CommunityBoardRepository sut;

    @Autowired
    UserRepository userRepository;

    private User savedUser;

    @BeforeEach
    void setUp() {
        savedUser = userRepository.save(UserFixture.create());
    }

    @Test
    @DisplayName("게시글 저장 후 ID로 조회할 수 있다")
    void 게시글_저장_후_ID_조회() {
        // given
        CommunityBoard board = CommunityBoardFixture.create(savedUser);
        CommunityBoard saved = sut.save(board);

        // when
        CommunityBoard found = sut.findById(saved.getBoardNo()).orElseThrow();

        // then
        assertThat(found.getTitle()).isEqualTo("테스트 게시글 제목");
        assertThat(found.getCategory()).isEqualTo("Java");
    }

    @Test
    @DisplayName("카테고리로 게시글을 필터링할 수 있다")
    void 카테고리_필터링() {
        // 로컬 DB에 동일 카테고리 데이터가 있을 수 있어 테스트 전용 카테고리로 격리
        String isolationCat = "REPO_TEST_" + UUID.randomUUID();
        sut.save(CommunityBoardFixture.createWithCategory(savedUser, isolationCat));
        sut.save(CommunityBoardFixture.createWithCategory(savedUser, "Python"));
        sut.save(CommunityBoardFixture.createWithCategory(savedUser, isolationCat));

        Page<CommunityBoard> filtered = sut.findAll(
                (root, query, cb) -> cb.equal(root.get("category"), isolationCat),
                PageRequest.of(0, 10)
        );

        assertThat(filtered.getTotalElements()).isEqualTo(2);
    }

    @Test
    @DisplayName("게시글 삭제 후 조회 시 빈 Optional을 반환한다")
    void 게시글_삭제_후_조회_시_빈_Optional() {
        // given
        CommunityBoard board = sut.save(CommunityBoardFixture.create(savedUser));
        Integer boardNo = board.getBoardNo();

        // when
        sut.deleteById(boardNo);

        // then
        assertThat(sut.findById(boardNo)).isEmpty();
    }
}
