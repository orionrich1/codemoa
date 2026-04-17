package com.codemoa.project.domain.problem.controller;

import com.codemoa.project.domain.problem.service.ProblemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import com.codemoa.project.domain.exception.MvcViewExceptionHandler;
import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.entity.UserGrade;
import com.codemoa.project.domain.user.security.CustomUserDetails;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ProblemController.class)
@Import({ MvcViewExceptionHandler.class, PermitAllSecurityMvcTestConfig.class })
@AutoConfigureMockMvc
@DisplayName("ProblemController Thymeleaf 렌더 스모크")
class ProblemControllerWebMvcTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ProblemService problemService;

	@Test
	@DisplayName("GET /problems — /problems/ 로 리다이렉트")
	void problemsRoot_redirects() throws Exception {
		mockMvc.perform(get("/problems"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/problems/"));
	}

	@Test
	@DisplayName("GET /problems/ — 목록 템플릿 200")
	void problemList_renders() throws Exception {
		given(problemService.getProblemList()).willReturn(List.of());

		mockMvc.perform(get("/problems/"))
				.andExpect(status().isOk())
				.andExpect(view().name("views/problem/problemList"));
	}

	@Test
	@DisplayName("GET /problems/aiAnswer — 템플릿 200")
	void aiAnswer_renders() throws Exception {
		mockMvc.perform(get("/problems/aiAnswer"))
				.andExpect(status().isOk())
				.andExpect(view().name("views/problem/aiAnswer"));
	}

	@Test
	@DisplayName("GET /problems/problemWrite — 비로그인 시 로그인 폼으로")
	void problemWrite_anonymous_redirects() throws Exception {
		mockMvc.perform(get("/problems/problemWrite"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/loginForm"));
	}

	@Test
	@DisplayName("GET /problems/problemWrite — 일반 회원은 403")
	void problemWrite_nonAdmin_forbidden() throws Exception {
		User user = new User("u1", "n", "nick", "e@e.com", "010", UserGrade.BRONZE);
		user.setUserPosition("USER");
		CustomUserDetails principal = new CustomUserDetails(user, "pw");

		mockMvc.perform(get("/problems/problemWrite").with(user(principal)))
				.andExpect(status().isForbidden())
				.andExpect(view().name("error/403"));
		SecurityContextHolder.clearContext();
	}

	@Test
	@DisplayName("GET /problems/problemUpdate — 비로그인 시 로그인 폼으로")
	void problemUpdate_anonymous_redirects() throws Exception {
		mockMvc.perform(get("/problems/problemUpdate").param("no", "1"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/loginForm"));
	}

	@Test
	@DisplayName("GET /problems/problemUpdate — 일반 회원은 403")
	void problemUpdate_nonAdmin_forbidden() throws Exception {
		User user = new User("u1", "n", "nick", "e@e.com", "010", UserGrade.BRONZE);
		user.setUserPosition("USER");
		CustomUserDetails principal = new CustomUserDetails(user, "pw");

		mockMvc.perform(get("/problems/problemUpdate").param("no", "1").with(user(principal)))
				.andExpect(status().isForbidden())
				.andExpect(view().name("error/403"));
		SecurityContextHolder.clearContext();
	}
}
