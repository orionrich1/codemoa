package com.codemoa.project.domain.user.controller;

import com.codemoa.project.domain.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("UserController Thymeleaf 렌더 스모크")
class UserControllerWebMvcTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	UserService userService;

	@Test
	@DisplayName("GET /loginForm")
	void loginForm_renders() throws Exception {
		mockMvc.perform(get("/loginForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("views/user/loginForm"));
	}

	@Test
	@DisplayName("GET /joinForm")
	void joinForm_renders() throws Exception {
		mockMvc.perform(get("/joinForm"))
				.andExpect(status().isOk())
				.andExpect(view().name("views/user/joinForm"));
	}

	@Test
	@DisplayName("GET /login → /loginForm 리다이렉트")
	void login_redirects() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/loginForm"));
	}
}
