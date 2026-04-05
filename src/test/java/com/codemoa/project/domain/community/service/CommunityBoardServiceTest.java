package com.codemoa.project.domain.community.service;

import com.codemoa.project.domain.community.dto.request.CreateBoardRequest;
import com.codemoa.project.domain.community.entity.CommunityBoard;
import com.codemoa.project.domain.community.repository.CommentRepository;
import com.codemoa.project.domain.community.repository.CommunityBoardRepository;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.entity.UserGrade;
import com.codemoa.project.domain.user.repository.UserRepository;
import com.codemoa.project.domain.user.service.UserService;
import com.codemoa.project.support.annotation.ServiceTest;
import com.codemoa.project.support.fixture.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ServiceTest
@DisplayName("CommunityBoardService 단위 테스트")
class CommunityBoardServiceTest {

    @InjectMocks
    CommunityBoardService sut;

    @Mock CommunityBoardRepository communityBoardRepository;
    @Mock UserRepository userRepository;
    @Mock CommentService commentService;
    @Mock CommentRepository commentRepository;
    @Mock UserService userService;

    @Nested
    @DisplayName("게시글 작성(create)")
    class Create {

        @Test
        @DisplayName("정상 게시글 작성 시 저장된다")
        void 정상_게시글_작성_시_저장된다() {
            // given
            User user = UserFixture.createWithGrade(UserGrade.GOLD);
            given(userRepository.findByUserId("user01")).willReturn(Optional.of(user));

            CreateBoardRequest request = new CreateBoardRequest();
            request.setTitle("테스트 제목");
            request.setContent("테스트 내용");
            request.setCategory("Java");

            // when
            sut.create(request, "user01");

            // then
            verify(communityBoardRepository).save(any(CommunityBoard.class));
        }

        @Test
        @DisplayName("존재하지 않는 사용자가 게시글 작성 시 예외가 발생한다")
        void 존재하지_않는_사용자_게시글_작성_시_예외발생() {
            // given
            given(userRepository.findByUserId("ghost")).willReturn(Optional.empty());

            CreateBoardRequest request = new CreateBoardRequest();
            request.setTitle("제목");
            request.setContent("내용");

            // when & then
            assertThatThrownBy(() -> sut.create(request, "ghost"))
                    .isInstanceOf(IllegalArgumentException.class);
            verify(communityBoardRepository, never()).save(any());
        }

        @Test
        @DisplayName("포인트 1000 미만 사용자가 질문글 작성 시 예외가 발생한다")
        void 포인트_부족_질문글_작성_시_예외발생() {
            // given
            User poorUser = UserFixture.createWithGrade(UserGrade.BRONZE);
            given(userRepository.findByUserId("poor")).willReturn(Optional.of(poorUser));

            CreateBoardRequest request = new CreateBoardRequest();
            request.setTitle("질문입니다");
            request.setContent("내용");
            request.setStakedPoints(50);

            // when & then
            assertThatThrownBy(() -> sut.create(request, "poor"))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("1000");
        }

        @Test
        @DisplayName("100 포인트 초과 베팅 시 예외가 발생한다")
        void 포인트_100초과_베팅_시_예외발생() {
            // given
            User richUser = new User("rich", "부자", "richNick", "rich@test.com", "010-0000-0000", UserGrade.MASTER);
            richUser.addPoints(5000);
            given(userRepository.findByUserId("rich")).willReturn(Optional.of(richUser));

            CreateBoardRequest request = new CreateBoardRequest();
            request.setTitle("질문입니다");
            request.setContent("내용");
            request.setStakedPoints(200);

            // when & then
            assertThatThrownBy(() -> sut.create(request, "rich"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("100");
        }
    }

    @Nested
    @DisplayName("게시글 조회(findById)")
    class FindById {

        @Test
        @DisplayName("존재하지 않는 게시글 조회 시 예외가 발생한다")
        void 존재하지_않는_게시글_조회_시_예외발생() {
            // given
            given(communityBoardRepository.findById(9999)).willReturn(Optional.empty());

            // when & then
            assertThatThrownBy(() -> sut.findById(9999))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @DisplayName("게시글 삭제(delete)")
    class Delete {

        @Test
        @DisplayName("작성자가 아닌 사용자가 삭제 시 예외가 발생한다")
        void 작성자_아닌_사용자_삭제_시_예외발생() {
            // given
            User owner = UserFixture.createWithId("owner");
            CommunityBoard board = CommunityBoard.create(owner, "제목", "내용", "Java",
                    CommunityBoard.PostType.NORMAL, 0);
            given(communityBoardRepository.findById(1)).willReturn(Optional.of(board));

            // when & then
            assertThatThrownBy(() -> sut.delete(1, "hacker"))
                    .isInstanceOf(IllegalStateException.class);
        }
    }
}
