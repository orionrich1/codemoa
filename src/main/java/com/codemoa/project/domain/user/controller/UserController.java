//기찬
package com.codemoa.project.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
<<<<<<< HEAD
import com.codemoa.project.domain.user.dto.request.UserLoginRequest;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.service.UserService;

=======
import org.springframework.web.bind.annotation.RequestMapping;

import com.codemoa.project.domain.user.dto.request.UserLoginRequest;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.service.UserService;

>>>>>>> 01035d48d4a5fe34decf2dc8118ba73d386c15ea
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; // 4단계에서 만든 Service 사용

<<<<<<< HEAD
    @GetMapping("/login")
    public String loginForm() { return "views/main/index"; }
    

    
    @PostMapping("/login")
    public String login(UserLoginRequest requestDto, HttpSession session) { // 3단계에서 만든 DTO 사용
        User loginUser = userService.login(requestDto);
        if (loginUser == null) return "redirect:/login";
        session.setAttribute("loginUser", loginUser);
        return "redirect:/";
    }
=======
   
   
    

    
    @GetMapping("/login")
    public String loginForm() {
        // templates/user/loginForm.html 파일을 찾아서 보여줍니다.
        return "user/loginForm";
    }

    // 2. "로그인 데이터를 처리하는" 역할만 담당 (POST 요청)
    @PostMapping("/login")
    public String login(UserLoginRequest requestDto, HttpSession session) {

        User loginUser = userService.login(requestDto);

        if (loginUser == null) {
            return "redirect:/login"; // 실패 시 다시 로그인 폼으로
        }

        // 성공 시 세션에 저장
        session.setAttribute("loginUser", loginUser);
        return "redirect:/"; // 메인 페이지로
    }
    
    @GetMapping("/logout")
	public String logout(HttpSession session) {
		// 세션에서 로그인 정보를 제거
		session.removeAttribute("loginUser");
		// 메인 페이지로 리다이렉트
		return "redirect:/";
	}
>>>>>>> 01035d48d4a5fe34decf2dc8118ba73d386c15ea
}