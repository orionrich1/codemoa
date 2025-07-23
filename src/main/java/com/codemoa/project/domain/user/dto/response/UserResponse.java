
package com.codemoa.project.domain.user.dto.response;

import com.codemoa.project.domain.user.entity.User;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {
	private String userId;
	private String name;
	private String nickname;
	private String email;
	private String mobile;
	private Integer totalPoints;
	private LocalDateTime membershipDate;
	private LocalDateTime unbanDate;

	private LocalDateTime banDate;
	private int banDay;
	private String banReason;

	private String gradeName;

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
