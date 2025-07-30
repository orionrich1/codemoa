package com.codemoa.project.domain.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codemoa.project.domain.user.dto.request.UserPassUpdateRequest;
import com.codemoa.project.domain.user.dto.request.UserUpdateRequest;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.repository.UserRepository;
import com.codemoa.project.domain.user.security.CustomUserDetails;
import com.codemoa.project.domain.user.service.MyPageService;
import com.codemoa.project.domain.user.service.SnsUserService;
import com.codemoa.project.domain.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/my-pages")
@RequiredArgsConstructor
public class MyPageController {
	private final MyPageService myPageService;
	private final SnsUserService snsUserService;
	private final UserService userService;

	private final UserRepository userRepository;

	@GetMapping
	public String toMain() {
		return "redirect:/my-pages/";
	}

	@GetMapping("/")
	public String myPage(Model model, @AuthenticationPrincipal CustomUserDetails principal) {
		User user = principal.getUser();
		model.addAttribute("myPageUser", myPageService.checkSnsLinked(user));
		return "views/user/mypage/myPageMain";
	}

	@GetMapping("/snsUnlink")
	public String snsUnlink(@AuthenticationPrincipal CustomUserDetails principal) {
		User user = principal.getUser();
		snsUserService.unlinkSnsAccount(user.getUserId());
		return "redirect:/my-pages/";
	}

	@GetMapping("/updateUserForm")
	public String updateUserForm(Model model, @AuthenticationPrincipal CustomUserDetails principal) {
		User user = principal.getUser();
		model.addAttribute("user", user);
		return "views/user/mypage/updateForm";
	}

	@ResponseBody
	@PostMapping("/checkPass")
	public Map<String, String> checkPass(UserPassUpdateRequest request) {
		Map<String, String> map = new HashMap<>();

		String result = myPageService.checkPass(request);
		map.put("result", result);
		return map;
	}

	@PostMapping("/updateUser")
	public String updateUser(UserUpdateRequest request) {
		userService.updateUser(request);
		
		User updatedUser = userRepository.findById(request.getUserId()).orElseThrow();
		CustomUserDetails updatedDetails = new CustomUserDetails(updatedUser);
		Authentication newAuth = new UsernamePasswordAuthenticationToken(
			    updatedDetails,
			    null,
			    updatedDetails.getAuthorities()
			);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
		
		return "redirect:/my-pages/";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request, HttpServletResponse response, Authentication authentication,
			@AuthenticationPrincipal CustomUserDetails principal) {
		User user = principal.getUser();

		new SecurityContextLogoutHandler().logout(request, response, authentication);
		userService.deleteUser(user.getUserId());
		return "redirect:/";
	}
}
