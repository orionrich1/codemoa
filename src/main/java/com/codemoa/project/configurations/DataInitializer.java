package com.codemoa.project.configurations;

import com.codemoa.project.domain.community.entity.CommunityBoard;
import com.codemoa.project.domain.community.repository.CommunityBoardRepository;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.entity.UserGrade;
import com.codemoa.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

/**
 * 애플리케이션 시작 시 테스트용 데이터를 생성하는 클래스입니다.
 * 개발 환경에서만 사용하고, 프로덕션 배포 시에는 비활성화하거나 제거하는 것을 권장합니다.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CommunityBoardRepository communityBoardRepository;
    private final UserRepository userRepository;
    // ▼▼▼ [수정됨] UserGradeRepository 필드 삭제 ▼▼▼
    // private final UserGradeRepository userGradeRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        long boardCount = communityBoardRepository.count();
        if (boardCount >= 110) {
            System.out.println("이미 충분한 게시물 데이터가 존재합니다. (" + boardCount + "개)");
            return;
        }

        // ▼▼▼ [수정됨] UserGradeRepository를 사용하던 코드 블록 전체 삭제 ▼▼▼
        // UserGrade를 DB에서 찾거나 생성할 필요 없이 Enum을 직접 사용합니다.

        // 2. 테스트용 사용자 생성 또는 조회
        User testUser = userRepository.findByUserId("testuser")
                .orElseGet(() -> {
                    // ▼▼▼ [수정됨] UserGrade.BRONZE Enum을 직접 사용 ▼▼▼
                    User newUser = new User("testuser", "테스트유저", "테스터", "test@test.com", "010-1234-5678", UserGrade.BRONZE);
                    newUser.setTotalPoints(5000);
                    return userRepository.save(newUser);
                });

        // 3. 110개의 테스트 게시물 생성 (기존과 동일)
        System.out.println("테스트용 게시물 데이터 생성을 시작합니다.");
        int postsToCreate = 110 - (int) boardCount;
        String[] categories = {"Java", "Spring", "Python", "JavaScript", "자유"};

        IntStream.rangeClosed(1, postsToCreate).forEach(i -> {
            String category = categories[i % categories.length];
            int stakedPoints = (i % 10 == 0 && !"자유".equals(category)) ? 10 * ((i/10) % 5 + 1) : 0;
            CommunityBoard.PostType postType = stakedPoints > 0 ? CommunityBoard.PostType.QUESTION : CommunityBoard.PostType.NORMAL;

            CommunityBoard board = CommunityBoard.create(
                    testUser,
                    "페이지네이션 테스트 게시물 " + i,
                    "이 내용은 페이지네이션 기능을 테스트하기 위해 자동으로 생성된 게시물입니다. 글 번호: " + i,
                    category,
                    postType,
                    stakedPoints
            );
            communityBoardRepository.save(board);
        });

        System.out.println(postsToCreate + "개의 테스트 게시물 데이터 생성을 완료했습니다.");
    }
}