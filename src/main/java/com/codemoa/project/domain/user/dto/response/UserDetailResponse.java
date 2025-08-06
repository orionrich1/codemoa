
package com.codemoa.project.domain.user.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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
	private Boolean isBan;
	private String grade;
	private String userPosition;
	
	private List<UserBanHistory> banHistory;

	
}
