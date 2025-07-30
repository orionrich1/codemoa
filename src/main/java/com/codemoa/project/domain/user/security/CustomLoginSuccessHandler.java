package com.codemoa.project.domain.user.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.service.SnsUserService;
import com.codemoa.project.domain.user.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 로그인을 할 경우 실행되는 핸들러
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	private final UserService userService;
	private final SnsUserService snsUserService;

	public CustomLoginSuccessHandler(UserService userService, SnsUserService snsUserService) {
		this.userService = userService;
		this.snsUserService = snsUserService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Object principal = authentication.getPrincipal();
		if (principal instanceof CustomUserDetails) {
			CustomUserDetails userDetails = (CustomUserDetails) principal;
			User user = userDetails.getUser(); // User 엔티티 추출

			// 차단일이 남은 유저인지 체크
			Boolean isBan = userService.checkIsBan(user);
			if (isBan) {
				String unbanDate = user.getUnbanDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
				String reason = userService.getBanReason(user.getUserId());
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("  alert('이 계정은 차단되었습니다.\\n차단 사유: " + reason + "\\n차단 해제일: " + unbanDate + "');");
				out.println("  history.replaceState(null, '', location.pathname);"); // URL 파라미터 정리 (필요 시)
				out.println("  location.href='/logout';");
				out.println("</script>");
				out.flush();
				return;
			}
			String userId = user.getUserId();

			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("providerId") != null) {
				String providerId = (String) session.getAttribute("providerId");
				String provider = (String) session.getAttribute("provider");

				// DB 연동 작업 수행
				snsUserService.linkSnsAccount(userId, provider, providerId);

				// 세션 SNS 정보 제거
				session.removeAttribute("providerId");
				session.removeAttribute("provider");
			}
		}

		response.sendRedirect("/");
	}
}
