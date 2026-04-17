package com.codemoa.project.domain.support.controller;

import com.codemoa.project.domain.support.service.SupportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(SupportController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("SupportController Thymeleaf 렌더 스모크")
class SupportControllerWebMvcTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	SupportService supportService;

	@Test
	@DisplayName("GET /support/")
	void supportHub_renders() throws Exception {
		mockMvc.perform(get("/support/"))
				.andExpect(status().isOk())
				.andExpect(view().name("views/support/support-hub"));
	}

	@Test
	@DisplayName("GET /support/faq")
	void faq_renders() throws Exception {
		given(supportService.hasAnyFaq()).willReturn(false);
		given(supportService.getFaqsGroupedByCategory()).willReturn(new LinkedHashMap<>());

		mockMvc.perform(get("/support/faq"))
				.andExpect(status().isOk())
				.andExpect(view().name("views/support/faq-list"));
	}

	@Test
	@DisplayName("GET /support/qna")
	void qnaList_renders() throws Exception {
		Map<String, Object> result = new HashMap<>();
		result.put("list", List.of());
		result.put("totalCount", 0);
		given(supportService.getQnaList(anyInt(), anyInt(), any(), any(), any()))
				.willReturn(result);

		mockMvc.perform(get("/support/qna"))
				.andExpect(status().isOk())
				.andExpect(view().name("views/support/qna-list"));
	}
}
