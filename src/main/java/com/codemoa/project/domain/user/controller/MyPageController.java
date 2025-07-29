package com.codemoa.project.domain.user.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.security.CustomOAuth2User;
import com.codemoa.project.domain.user.security.CustomUserDetails;
import com.codemoa.project.domain.user.service.MyPageService;
import com.codemoa.project.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/my-pages")
@RequiredArgsConstructor
public class MyPageController {
	private final MyPageService myPageService;
	private final UserService userService;

	@GetMapping
	public String toMain() {
		return "redirect:/my-pages/";
	}

	@GetMapping("/")
	public String myPage(Model model, @AuthenticationPrincipal Object principalUser) {
		User user = null;
		if (principalUser instanceof CustomUserDetails) {
			user = ((CustomUserDetails) principalUser).getUser();
		} else if (principalUser instanceof CustomOAuth2User) {
			user = ((CustomOAuth2User) principalUser).getUser();
		}
		model.addAttribute("myPageUser", myPageService.checkSnsLinked(user));
		return "views/user/mypage/myPage";
	}
	
	@GetMapping("/snsUnlink")
	public String snsUnlink(@AuthenticationPrincipal Object principalUser) {
		User user = null;
		if (principalUser instanceof CustomUserDetails) {
			user = ((CustomUserDetails) principalUser).getUser();
		} else if (principalUser instanceof CustomOAuth2User) {
			user = ((CustomOAuth2User) principalUser).getUser();
		}
		
		// 세션 만료로 로그아웃 되었을 경우
		if (user == null) {
			return "redirect:/my-pages/";
		}
		
		userService.unlinkSnsAccount(user.getUserId());
		return "redirect:/my-pages/";
	}
}
