
package com.codemoa.project.domain.user.dto.response;

import com.codemoa.project.domain.user.entity.User;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class UserResponse {

    // 1. 데이터를 담을 필드(변수) 선언
    private final String userId;
    private final String name;
    private final String nickname;
    private final String email;
    private final String mobile;
    private final Integer totalPoints;
    private final LocalDateTime membershipDate;
    private final String gradeName;

    // 2. 클래스 내부에 생성자 선언
    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.mobile = user.getMobile();
        this.totalPoints = user.getTotalPoints();
        this.membershipDate = user.getMembershipDate();
        this.gradeName = user.getUserGrade().getGradeName();
    }
}