
package com.codemoa.project.domain.user.dto.response;

import com.codemoa.project.domain.user.entity.User;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDetailResponse {
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
}
