package com.codemoa.project.domain.user.security;

import com.codemoa.project.domain.user.entity.PointEventType; // 신규 추가
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.service.SnsUserService;
import com.codemoa.project.domain.user.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	private final UserService userService;
	private final SnsUserService snsUserService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		Object principal = authentication.getPrincipal();

		if (!(principal instanceof CustomUserDetails)) {
			// 예상치 못한 Principal 타입일 경우 기본 URL로 리다이렉트
			response.sendRedirect("/");
			return;
		}

		CustomUserDetails userDetails = (CustomUserDetails) principal;
		User user = userDetails.getUser(); // User 엔티티 추출

		// 1. 계정 차단 여부 확인 (기존 중요 로직)
		Boolean isBan = userService.checkIsBan(user);
		if (isBan) {
			String unbanDate = user.getUnbanDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
			String reason = userService.getBanReason(user.getUserId());
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('이 계정은 차단되었습니다.\\n차단 사유: " + reason + "\\n차단 해제일: " + unbanDate + "');");
			out.println("location.href='/logout';");
			out.println("</script>");
			out.flush();
			return;
		}

		String userId = user.getUserId();

		// 2. SNS 계정 연동 처리 (기존 중요 로직)
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("providerId") != null) {
			String providerId = (String) session.getAttribute("providerId");
			String provider = (String) session.getAttribute("provider");

			snsUserService.linkSnsAccount(userId, provider, providerId);
			session.removeAttribute("providerId");
			session.removeAttribute("provider");
		}

		// 3. 일일 로그인 포인트 지급 (★신규 기능 추가★)
		userService.addPointsForEvent(userId, PointEventType.DAILY_LOGIN);

		// 4. 모든 처리가 끝나면 메인 페이지로 이동
		response.sendRedirect("/");
	}
}