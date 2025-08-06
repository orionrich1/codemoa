package com.codemoa.project.domain.user.security;

import com.codemoa.project.domain.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class CustomUserDetails implements OAuth2User, UserDetails {

	private final User user;
	private final String password;
	private final String provider;
	private final String providerId;

	// 유저 업데이트
	public CustomUserDetails(User user) {
		this.user = user;
		this.password = null;
		this.provider = null;
		this.providerId = null;
	}

	// Local 로그인 계정
	public CustomUserDetails(User user, String password) {
		this.user = user;
		this.password = password;
		this.provider = null;
		this.providerId = null;
	}

	// SNS 로그인 계정
	public CustomUserDetails(User user, String provider, String providerId) {
		this.user = user;
		this.password = null;
		this.provider = provider;
		this.providerId = providerId;
	}

	public User getUser() {
		return user;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return null;
	}

	// ▼▼▼▼▼ [수정된 부분] ▼▼▼▼▼
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// UserGrade Enum의 이름을 기반으로 권한을 생성합니다. (예: "ROLE_BRONZE")
		// user.getUserGrade().getGradeId() -> user.getGrade().name()
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getUserPosition()));
	}
	// ▲▲▲▲▲ [수정된 부분] ▲▲▲▲▲

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return user.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getName() {
		return null;
	}
}