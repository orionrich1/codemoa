package com.codemoa.project.domain.employment.controller;

import com.codemoa.project.domain.employment.dto.response.EmploymentDto;
import com.codemoa.project.domain.employment.service.EmploymentApiService;
import com.codemoa.project.domain.employment.service.EmploymentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(EmploymentController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("EmploymentController Thymeleaf 렌더 스모크")
class EmploymentControllerWebMvcTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	EmploymentApiService employmentApiService;
	@MockBean
	EmploymentService employmentService;

	@Test
	@DisplayName("GET /employmentList")
	void employmentList_renders() throws Exception {
		Page<EmploymentDto> empty = new PageImpl<>(List.of(), PageRequest.of(0, 9), 0);
		given(employmentService.getEmploymentWithFilters(any(), any(), any())).willReturn(empty);
		given(employmentService.buildPagination(anyInt(), anyInt(), anyInt()))
				.willReturn(Map.of("totalPages", 0, "currentPage", 1, "startPage", 1, "endPage", 1));
		given(employmentService.getLatestDatasetRegDt()).willReturn(Optional.empty());

		mockMvc.perform(get("/employmentList"))
				.andExpect(status().isOk())
				.andExpect(view().name("views/employment/employmentList"));
	}
}
