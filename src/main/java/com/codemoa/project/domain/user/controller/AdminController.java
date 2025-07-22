//도영
package com.codemoa.project.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}