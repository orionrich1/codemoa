//기찬 마이페이지 정보 응답 DTO
package com.codemoa.project.domain.user.dto.response;

import com.codemoa.project.domain.user.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyPageResponse {
	private User user;
	private boolean isSnsLinked = false;
	private String snsType;
}
