package com.codemoa.project.domain.user.security;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final UserService userService;
	private final HttpServletRequest request;

	// 결과와 유저 정보를 동시에 받아오기 위해 사용

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);

		// provider, userNameAttributeName, attributes 등을 가져옴
		String provider = userRequest.getClientRegistration().getRegistrationId(); // "google", "kakao" 등
		Map<String, Object> attributes = oauth2User.getAttributes();

		// 사용자 정보 매핑 및 가입 처리
		OAuth2UserLoginResult user = userService.processOAuthUser(provider, attributes);

		if (user.getStatus() == OAuth2UserLoginResult.Status.SUCCESS) {
			return new CustomOAuth2User(user.getUser(), provider, user.getProviderId());
		} else if (user.getStatus() == OAuth2UserLoginResult.Status.NEED_SIGN) {
			request.getSession().setAttribute("provider", provider);
			request.getSession().setAttribute("providerId", user.getProviderId());

			// 회원가입 페이지로 리다이렉트
//			throw new OAuth2AuthenticationRedirectException("/signup/oauth");
			return null;
		} else {
			throw new OAuth2AuthenticationException("SNS 인증 실패");
		}
	}
}
