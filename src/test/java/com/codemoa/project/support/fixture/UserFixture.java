package com.codemoa.project.support.fixture;

import java.time.LocalDateTime;

import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.entity.UserGrade;

/**
 * User 엔티티 테스트 데이터 팩토리.
 * 테스트 코드에서 User 객체를 직접 생성하지 않고 이 클래스를 통해 생성한다.
 */
public class UserFixture {

    private static void fillRequiredDates(User user) {
        LocalDateTime now = LocalDateTime.now();
        user.setMembershipDate(now);
        user.setUnbanDate(now);
    }

    public static User create() {
        User u = new User("user01", "홍길동", "개발자홍", "hong@codemoa.com", "010-1234-5678", UserGrade.BRONZE);
        fillRequiredDates(u);
        return u;
    }

    public static User createWithId(String userId) {
        User u = new User(userId, "홍길동", userId + "_nick", userId + "@codemoa.com", "010-0000-0000", UserGrade.BRONZE);
        fillRequiredDates(u);
        return u;
    }

    public static User createAdmin() {
        User u = new User("admin01", "관리자", "관리자닉", "admin@codemoa.com", "010-9999-9999", UserGrade.ADMIN);
        fillRequiredDates(u);
        return u;
    }

    public static User createWithGrade(UserGrade grade) {
        User u = new User("grade_user", "등급유저", grade.name() + "_nick", "grade@codemoa.com", "010-1111-2222", grade);
        fillRequiredDates(u);
        return u;
    }
}
