//도영
package com.codemoa.project.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codemoa.project.domain.user.dto.request.UserBanRequest;
import com.codemoa.project.domain.user.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	private final AdminService adminService;

	@GetMapping
	public String redirect() {
		return "redirect:/admin/";
	}

	@GetMapping("/")
	public String adminMain() {
		return "views/user/admin/main";
	}

	@GetMapping("/users")
	public String adminUsers(Model model, @RequestParam(value = "userId", required = false) String userId) {
		if (userId == null || userId.equals("")) {
			model.addAttribute("userList", adminService.getUserList());
			return "views/user/admin/users";
		} else {
			model.addAttribute("user", adminService.getUserDetail(userId));
			return "views/user/admin/userDetail";
		}
	}

	@PostMapping("/banUser")
	public String banUser(UserBanRequest request) {
		adminService.banUser(request);
		return "redirect:users?userId=" + request.getUserId();
	}

	@GetMapping("/unbanUser")
	public String banUser(@RequestParam("userId") String userId) {
		adminService.unbanUser(userId);
		return "redirect:users?userId=" + userId;
	}

	@GetMapping("/banners")
	public String adminBanners() {
		return "views/user/admin/banners";
	}

	@GetMapping("/contents")
	public String adminContents() {
		return "views/user/admin/contents";
	}
}