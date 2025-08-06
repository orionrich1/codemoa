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

import java.util.Optional;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CommunityBoardRepository communityBoardRepository;
    private final UserRepository userRepository;
    private final LocalUserRepository localUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
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

        long boardCount = communityBoardRepository.count();
        if (boardCount > 0) {
            System.out.println("이미 테스트 게시물 데이터가 존재합니다.");
            return;
        }

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
     * 사용자가 존재하지 않으면 새로 생성하고, 
     * 'admin' 사용자가 존재하지만 등급이 ADMIN이 아닐 경우 강제로 업데이트하는 메서드
     */
    private void createUserIfNotExists(String userId, String name, String nickname, String rawPassword, int points) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);

        if (optionalUser.isEmpty()) {
            // 시나리오 1: 사용자가 존재하지 않으면 새로 생성
            UserGrade grade = "admin".equals(userId) ? UserGrade.ADMIN : UserGrade.getGradeForPoints(points);
            
            User newUser = new User(userId, name, nickname, userId + "@test.com", "010-1234-5678", grade);
            newUser.setTotalPoints(points);

            String encodedPassword = passwordEncoder.encode(rawPassword);
            LocalUser newLocalUser = new LocalUser(newUser, encodedPassword);

            localUserRepository.save(newLocalUser);

        } else if ("admin".equals(userId)) {
            // 시나리오 2: 사용자가 존재하고, 그 사용자가 'admin'인 경우
            User adminUser = optionalUser.get();
            if (adminUser.getGrade() != UserGrade.ADMIN) {
                // 등급이 ADMIN이 아니면, ADMIN으로 강제 업데이트
                System.out.println("기존 'admin' 계정의 등급을 ADMIN으로 업데이트합니다.");
                adminUser.setGrade(UserGrade.ADMIN);
                userRepository.save(adminUser); // 변경사항 저장
            }
        }
    }
}