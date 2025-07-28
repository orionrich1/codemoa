package com.codemoa.project.domain.user.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// SNS 계정과 연동된 상태에서 로그인을 할 경우 실행되는 핸들러
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	private final UserService userService;

	public CustomLoginSuccessHandler(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Object principal = authentication.getPrincipal();
		if (principal instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails) principal;
			User user = userDetails.getUser(); // User 엔티티 추출
			String userId = user.getUserId();

			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("providerId") != null) {
				String providerId = (String) session.getAttribute("providerId");
				String provider = (String) session.getAttribute("provider");

				// DB 연동 작업 수행
				userService.linkSnsAccount(userId, provider, providerId);

				// 세션 SNS 정보 제거
				session.removeAttribute("providerId");
				session.removeAttribute("provider");
			}
		}

		response.sendRedirect("/");
	}
}
