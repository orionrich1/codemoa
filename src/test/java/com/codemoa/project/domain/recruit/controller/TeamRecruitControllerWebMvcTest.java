package com.codemoa.project.domain.recruit.controller;

import com.codemoa.project.common.service.FileStorageService;
import com.codemoa.project.domain.recruit.service.TeamRecruitService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TeamRecruitController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("TeamRecruitController Thymeleaf 렌더 스모크")
class TeamRecruitControllerWebMvcTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	TeamRecruitService teamRecruitService;
	@MockBean
	FileStorageService fileStorageService;

	@Test
	@DisplayName("GET /teamRecruitList")
	void teamRecruitList_renders() throws Exception {
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("recruitList", List.of());
		modelMap.put("pageCount", 0);
		modelMap.put("startPage", 1);
		modelMap.put("endPage", 1);
		modelMap.put("currentPage", 1);
		modelMap.put("listCount", 0);
		modelMap.put("pageGroup", 10);
		given(teamRecruitService.teamRecruitList(anyInt(), anyString(), anyString())).willReturn(modelMap);

		mockMvc.perform(get("/teamRecruitList"))
				.andExpect(status().isOk())
				.andExpect(view().name("views/recruit/teamRecruitList"));
	}
}
