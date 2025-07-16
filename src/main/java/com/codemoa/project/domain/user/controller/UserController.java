//기찬
package com.codemoa.project.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.codemoa.project.domain.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@SessionAttributes("user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
	
	 private final UserService userService;

	 
	 
	 @PostMapping("/login")
	    public String login(LoginRequestDto loginDto, HttpSession session) {
		 
		 
	 }
}
