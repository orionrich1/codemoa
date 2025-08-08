package com.codemoa.project.domain.user.controller;

import com.codemoa.project.domain.user.dto.request.UserFindRequest;
import com.codemoa.project.domain.user.dto.request.UserPassUpdateRequest;
import com.codemoa.project.domain.user.dto.request.UserSignUpRequest;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/loginForm")
	public String loginForm() {
		return "views/user/loginForm";
	}

	// SNS 계정으로 접속한 상태일 때, 연동 해제 요청
	@GetMapping("/snsDisconnect")
	public String snsDisconnect(HttpSession session) {
		session.removeAttribute("provider");
		session.removeAttribute("providerId");
		return "redirect:/loginForm";
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "views/user/joinForm";
	}
	
	@PostMapping("/checkId")
	public ResponseEntity<Boolean> checkId(@RequestParam("userId") String data){
		boolean result = userService.checkId(data);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/join")
	public String join(UserSignUpRequest request, HttpServletRequest httpRequest) {
		HttpSession session = httpRequest.getSession(false);
		String snsProvider = "";
		String snsId = "";

		if (session != null) {
			snsProvider = (String) session.getAttribute("provider");
			snsId = (String) session.getAttribute("providerId");
		}

		userService.signUp(request, snsProvider, snsId);
		
		if (session != null) {
			session.removeAttribute("provider");
			session.removeAttribute("providerId");
		}
		return "redirect:/loginForm";
	}

//    @PostMapping("/login")
//    public String login(UserLoginRequest request, HttpSession session, RedirectAttributes redirectAttributes) { // 파라미터로 RedirectAttributes 추가
//        try {
//            UserResponse loginUser = userService.login(request);
//            session.setAttribute("loginUser", loginUser);
//            return "redirect:/";
//        } catch (IllegalArgumentException e) {
//            // 파라미터로 받은 redirectAttributes 객체를 사용
//            redirectAttributes.addFlashAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
//            return "redirect:/loginForm";
//        }
//    }

	// 스프링 Security 기본 기능과 충돌할 수 있으므로 주석 처리
	/*
	 * @PostMapping("/logout") public String logout(HttpSession session) {
	 * session.invalidate(); return "redirect:/loginForm"; }
	 */

	// 아이디 찾기
	@GetMapping("/findId")
	public String findId() {
		return "views/user/findId";
	}

	// 비밀번호 찾기
	@GetMapping("/findPass")
	public String findPass() {
		return "views/user/findPass";
	}

	// 찾기 결과
	@PostMapping("/findResult")
	public String findResult(UserFindRequest request, Model model) {
		List<User> user = userService.findResult(request);
		boolean isFindId = request.getUserId() == null || request.getUserId().isBlank();

		if (isFindId) {
			if (user != null && !user.isEmpty())
				model.addAttribute("user", user);
		} else {
			if (user != null && !user.isEmpty())
				model.addAttribute("user", user.get(0));
		}
		model.addAttribute("isFindId", isFindId);

		return "views/user/findResult";
	}

	// 비밀번호 재설정
	@PostMapping("/updatePass")
	public String updatePass(UserPassUpdateRequest request) {
		userService.updatePass(request);
		return "redirect:/loginForm";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String handleException(IllegalArgumentException exception, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", exception.getMessage());
		// 어떤 예외가 발생했는지에 따라 리다이렉트 경로를 다르게 할 수 있습니다.
		// 여기서는 간단하게 joinForm으로 통일합니다.
		return "redirect:/joinForm";
	}
}