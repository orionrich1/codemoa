package com.codemoa.project.domain.user.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.codemoa.project.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

// SNS와 연동해서 로그인 성공했을 때 사용하는 User 객체
// 기존 CustomUserDetails 와 기능 자체는 매우 유사하나 pass 값의 null 처리 등으로 인해 분리함
@AllArgsConstructor
@Getter
public class CustomOAuth2User implements OAuth2User, UserDetails {

	private final User user;
	private final String provider;
	private final String providerId;

	public User getUser() {
		return user;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getUserGrade().getGradeId()));
	}

	@Override
	public String getName() {
		return user.getUserId();
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return user.getName();
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

}
