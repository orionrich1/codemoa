package com.codemoa.project.configurations;

import com.codemoa.project.domain.community.entity.CommunityBoard;
import com.codemoa.project.domain.community.repository.CommunityBoardRepository;
import com.codemoa.project.domain.user.entity.LocalUser;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.entity.UserGrade;
import com.codemoa.project.domain.user.repository.LocalUserRepository;
import com.codemoa.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CommunityBoardRepository communityBoardRepository;
    private final UserRepository userRepository;
    // ▼▼▼ [추가된 의존성] ▼▼▼
    private final LocalUserRepository localUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // 1. 샘플 사용자 생성
        System.out.println("샘플 사용자 데이터 생성을 시작합니다.");
        createUserIfNotExists("admin", "관리자", "Admin", "1234", 999999);
        createUserIfNotExists("user01", "김브론즈", "브론즈맨", "1234", 500);
        createUserIfNotExists("user02", "박브론즈", "나는브론즈", "1234", 1500);
        createUserIfNotExists("user03", "이실버", "실버스타", "1234", 2500);
        createUserIfNotExists("user04", "최실버", "실버서퍼", "1234", 4800);
        createUserIfNotExists("user05", "정골드", "골드러시", "1234", 6000);
        createUserIfNotExists("user06", "강골드", "황금인생", "1234", 9500);
        createUserIfNotExists("user07", "조플래", "플래티넘맨", "1234", 12000);
        createUserIfNotExists("user08", "윤플래", "백금전사", "1234", 18000);
        createUserIfNotExists("user09", "장다이아", "다이아손", "1234", 25000);
        createUserIfNotExists("user10", "임다이아", "빛나는다이아", "1234", 45000);
        System.out.println("샘플 사용자 데이터 생성을 완료했습니다.");

        // 2. 테스트 게시물 생성
        long boardCount = communityBoardRepository.count();
        if (boardCount > 0) { // 게시물이 하나라도 있으면 생성하지 않음
            System.out.println("이미 테스트 게시물 데이터가 존재합니다.");
            return;
        }

        // 게시물 작성을 위해 사용자 조회
        User postUser = userRepository.findByUserId("user05").orElseThrow();

        System.out.println("테스트용 게시물 데이터 생성을 시작합니다.");
        String[] categories = {"Java", "Spring", "Python", "JavaScript", "자유"};
        IntStream.rangeClosed(1, 110).forEach(i -> {
            String category = categories[i % categories.length];
            int stakedPoints = (i % 10 == 0 && !"자유".equals(category)) ? 10 * ((i/10) % 5 + 1) : 0;
            CommunityBoard.PostType postType = stakedPoints > 0 ? CommunityBoard.PostType.QUESTION : CommunityBoard.PostType.NORMAL;

            CommunityBoard board = CommunityBoard.create(
                    postUser,
                    "페이지네이션 테스트 게시물 " + i,
                    "이 내용은 페이지네이션 기능을 테스트하기 위해 자동으로 생성된 게시물입니다. 글 번호: " + i,
                    category,
                    postType,
                    stakedPoints
            );
            communityBoardRepository.save(board);
        });
        System.out.println("110개의 테스트 게시물 데이터 생성을 완료했습니다.");
    }

    /**
     * 사용자가 존재하지 않으면 새로 생성하는 헬퍼 메서드
     */
    private void createUserIfNotExists(String userId, String name, String nickname, String rawPassword, int points) {
        if (userRepository.findByUserId(userId).isEmpty()) {
            UserGrade grade = UserGrade.getGradeForPoints(points); // 포인트에 맞는 등급 계산
            
            User newUser = new User(userId, name, nickname, userId + "@test.com", "010-1234-5678", grade);
            newUser.setTotalPoints(points);

            // LocalUser를 생성할 때 암호화된 비밀번호를 사용
            String encodedPassword = passwordEncoder.encode(rawPassword);
            LocalUser newLocalUser = new LocalUser(newUser, encodedPassword);

            // LocalUser를 저장하면 User도 함께 저장됩니다 (CascadeType.ALL)
            localUserRepository.save(newLocalUser);
        }
    }
}