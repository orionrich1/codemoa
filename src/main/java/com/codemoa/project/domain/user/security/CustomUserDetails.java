package com.codemoa.project.domain.user.security;

import com.codemoa.project.domain.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final User user;
    private final String password;

    public CustomUserDetails(User user, String password) {
        this.user = user;
        this.password = password;
    }

    // 로그인 후 사용자 정보를 가져오기 위한 Getter
    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // UserGrade에서 권한 정보를 가져옵니다.
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getUserGrade().getGradeName()));
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
}