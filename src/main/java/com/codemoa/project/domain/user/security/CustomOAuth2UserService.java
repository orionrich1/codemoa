package com.codemoa.project.domain.user.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.codemoa.project.domain.user.service.SnsUserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

// SNS 계정으로 로그인 하는 로직을 처리하는 클래스
// user.service 패키지 안에 있는 UserDetailsServiceImpl 파일과 기능 자체는 유사함
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final SnsUserService snsUserService;
	
	@Autowired
	private HttpServletRequest request;

	// 결과와 유저 정보를 동시에 받아오기 위해 사용

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);

		// provider, userNameAttributeName, attributes 등을 가져옴
		String provider = userRequest.getClientRegistration().getRegistrationId(); // "google", "kakao" 등
		Map<String, Object> attributes = oauth2User.getAttributes();

		// 사용자 정보 매핑 및 가입 처리
		OAuth2UserLoginResult user = snsUserService.processOAuthUser(provider, attributes);

		if (user.getStatus() == OAuth2UserLoginResult.Status.SUCCESS) {
			return new CustomUserDetails(user.getUser(), provider, user.getProviderId());
		} else if (user.getStatus() == OAuth2UserLoginResult.Status.NEED_SIGN) {
			request.getSession().setAttribute("provider", provider);
			request.getSession().setAttribute("providerId", user.getProviderId());
			 throw new OAuth2AuthenticationException(new OAuth2Error("need_signup"), "회원가입 필요");
		} else {
			throw new OAuth2AuthenticationException("SNS 인증 실패");
		}
	}
}
