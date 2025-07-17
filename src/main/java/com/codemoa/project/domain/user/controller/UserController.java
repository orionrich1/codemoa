//기찬
package com.codemoa.project.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.codemoa.project.domain.user.dto.request.UserLoginRequest;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; // 4단계에서 만든 Service 사용

    @GetMapping("/login")
    public String loginForm() { return "views/main/index"; }
    

    
    @PostMapping("/login")
    public String login(UserLoginRequest requestDto, HttpSession session) { // 3단계에서 만든 DTO 사용
        User loginUser = userService.login(requestDto);
        if (loginUser == null) return "redirect:/login";
        session.setAttribute("loginUser", loginUser);
        return "redirect:/";
    }
}