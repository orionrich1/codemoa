package com.codemoa.project.domain.user.security;

import com.codemoa.project.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// SNS 계정 접속을 시도했을 때, 결과값 반환용으로 사용하는 클래스
@NoArgsConstructor
@Getter
@Setter
public class OAuth2UserLoginResult {
	public enum Status {
		SUCCESS, // 유저도 있고 로그인 완료
		NEED_SIGN, // 로그인은 성공했지만 회원가입 필요
		FAIL // 로그인 자체 실패
	}

	private Status status;
	private User user;
	private String providerId;
}
