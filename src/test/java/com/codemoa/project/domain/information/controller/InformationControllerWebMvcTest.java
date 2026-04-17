package com.codemoa.project.domain.information.controller;

import com.codemoa.project.domain.information.service.InformationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(InformationController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("InformationController Thymeleaf 렌더 스모크")
class InformationControllerWebMvcTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	InformationService informationService;

	@Test
	@DisplayName("GET /information — 허브 템플릿 200")
	void informationHub_renders() throws Exception {
		given(informationService.findLatestLectures(anyInt())).willReturn(List.of());
		given(informationService.findLatestBooks(anyInt())).willReturn(List.of());
		given(informationService.findContestsForMainHub(anyInt(), anyInt())).willReturn(List.of());
		given(informationService.countContestsEndingWithinDays(anyInt())).willReturn(0);

		mockMvc.perform(get("/information"))
				.andExpect(status().isOk())
				.andExpect(view().name("views/information/informationHub"));
	}
}
