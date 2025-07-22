package com.codemoa.project.domain.user.controller;

import com.codemoa.project.domain.user.dto.response.UserSessionResponse;
import com.codemoa.project.domain.user.dto.request.UserLoginRequest;
import com.codemoa.project.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(UserLoginRequest requestDto, HttpSession session) {
        // ✅ Service로부터 DTO를 받도록 타입을 수정합니다.
        UserSessionResponse loginUser = userService.login(requestDto);

        if (loginUser == null) {
            return "redirect:/loginForm";
        }

        // 세션에 안전한 DTO를 저장합니다.
        session.setAttribute("loginUser", loginUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}