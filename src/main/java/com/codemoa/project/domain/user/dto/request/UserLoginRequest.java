//기찬 로그인 요청 DTO
package com.codemoa.project.domain.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
	// 로그인 요청 시 필요한 필드
	private String userId;
	private String pass;

}
