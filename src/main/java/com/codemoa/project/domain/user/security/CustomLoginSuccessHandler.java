package com.codemoa.project.domain.user.security;

import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.service.SnsUserService;
import com.codemoa.project.domain.user.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor; // 생성자 주입을 위해 @RequiredArgsConstructor 사용을 권장합니다.
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 만들어줍니다.
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	private final UserService userService;
	private final SnsUserService snsUserService;

	// @RequiredArgsConstructor를 사용하면 이 생성자는 직접 작성할 필요가 없습니다.
	// public CustomLoginSuccessHandler(UserService userService, SnsUserService snsUserService) {
	// 	this.userService = userService;
	// 	this.snsUserService = snsUserService;
	// }

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// Principal 객체 타입 확인
		Object principal = authentication.getPrincipal();
		if (!(principal instanceof CustomUserDetails)) {
			// 예상치 못한 Principal 타입일 경우 기본 URL로 리다이렉트
			response.sendRedirect("/");
			return;
		}

		CustomUserDetails userDetails = (CustomUserDetails) principal;
		User user = userDetails.getUser(); // User 엔티티 추출

		// --- [수정된 부분] ---
		// 1. 수정된 checkIsBan 메서드 호출
		boolean isBanned = userService.checkIsBan(user);

		if (isBanned) {
			// 2. user 객체에서 직접 banLeftDay와 banReason을 가져옴
			String unbanDate = user.getBanLeftDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
			String reason = user.getBanReason();

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("  alert('이 계정은 차단되었습니다.\\n차단 사유: " + (reason != null ? reason : "미지정") + "\\n차단 해제일: " + unbanDate + "');");
			out.println("  location.href='/logout';");
			out.println("</script>");
			out.flush();
			return; // 핸들러 로직 종료
		}
		// --- [수정 끝] ---

		String userId = user.getUserId();

		// SNS 계정 연동 로직
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("providerId") != null) {
			String providerId = (String) session.getAttribute("providerId");
			String provider = (String) session.getAttribute("provider");

			snsUserService.linkSnsAccount(userId, provider, providerId);

			session.removeAttribute("providerId");
			session.removeAttribute("provider");
		}
		
		// 모든 처리가 끝나면 메인 페이지로 이동
		response.sendRedirect("/");
	}
}