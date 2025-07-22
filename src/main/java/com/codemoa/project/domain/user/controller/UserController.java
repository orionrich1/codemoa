package com.codemoa.project.domain.user.controller;

import com.codemoa.project.domain.user.dto.request.UserLoginRequest;
import com.codemoa.project.domain.user.dto.request.UserSignUpRequest;
import com.codemoa.project.domain.user.dto.response.UserResponse;
import com.codemoa.project.domain.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/loginForm")
    public String loginForm() {
        return "views/user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "views/user/joinForm";
    }

    @PostMapping("/join")
    public String join(UserSignUpRequest request) {
        userService.signUp(request);
        return "redirect:/loginForm";
    }

    @PostMapping("/login")
    public String login(UserLoginRequest request, HttpSession session) {
        try {
            UserResponse loginUser = userService.login(request);
            session.setAttribute("loginUser", loginUser);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            RedirectAttributes redirectAttributes = null; // -> null로 생성
            redirectAttributes.addFlashAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다."); // -> null에 접근하여 오류 발생
            return "redirect:/loginForm";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleException(IllegalArgumentException exception, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", exception.getMessage());
        // 어떤 예외가 발생했는지에 따라 리다이렉트 경로를 다르게 할 수 있습니다.
        // 여기서는 간단하게 joinForm으로 통일합니다.
        return "redirect:/joinForm";
    }
}