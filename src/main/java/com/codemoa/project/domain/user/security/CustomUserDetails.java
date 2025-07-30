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

	// 로그인 후 사용자 정보를 가져오기 위한 Getter
	public User getUser() {
		return user;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// UserGrade에서 권한 정보를 가져옵니다.
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getUserGrade().getGradeId()));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return user.getUserId();
	}

	// 계정 만료, 잠금, 자격 증명 만료, 활성화 여부는 필요에 따라 구현합니다. (기본값 true)
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