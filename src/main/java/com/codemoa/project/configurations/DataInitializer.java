package com.codemoa.project.configurations;

import com.codemoa.project.domain.community.entity.CommunityBoard;
import com.codemoa.project.domain.community.repository.CommunityBoardRepository;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.entity.UserGrade;
import com.codemoa.project.domain.user.repository.UserGradeRepository;
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
    private final UserGradeRepository userGradeRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // --- 현재 프로필이 'dev'일 때만 실행되도록 하는 로직 (선택 사항) ---
        // String activeProfile = System.getProperty("spring.profiles.active");
        // if (!"dev".equals(activeProfile)) {
        //     return;
        // }

        long boardCount = communityBoardRepository.count();
        if (boardCount >= 110) {
            System.out.println("이미 충분한 게시물 데이터가 존재합니다. (" + boardCount + "개)");
            return;
        }

        // 1. 테스트용 사용자 등급 생성 또는 조회
        UserGrade testGrade = userGradeRepository.findById("BRONZE")
                .orElseGet(() -> {
                    // UserGrade 엔티티에 해당 생성자가 필요합니다.
                    UserGrade newGrade = new UserGrade("BRONZE", "브론즈", 0);
                    return userGradeRepository.save(newGrade);
                });

        // 2. 테스트용 사용자 생성 또는 조회
        User testUser = userRepository.findByUserId("testuser")
                .orElseGet(() -> {
                    User newUser = new User("testuser", "테스트유저", "테스터", "test@test.com", "010-1234-5678", 0, testGrade);
                    newUser.setPoint(5000); // 질문 글 작성을 위한 충분한 포인트 설정
                    return userRepository.save(newUser);
                });

        // 3. 110개의 테스트 게시물 생성
        System.out.println("테스트용 게시물 데이터 생성을 시작합니다.");
        int postsToCreate = 110 - (int) boardCount;
        String[] categories = {"Java", "Spring", "Python", "JavaScript", "자유"};

        IntStream.rangeClosed(1, postsToCreate).forEach(i -> {
            String category = categories[i % categories.length];
            // 10번에 한 번씩, '자유' 카테고리가 아닐 때 질문 포인트를 겁니다.
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
