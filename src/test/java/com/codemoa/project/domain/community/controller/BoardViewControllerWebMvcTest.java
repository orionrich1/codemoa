package com.codemoa.project.domain.community.controller;

import com.codemoa.project.domain.community.dto.response.BoardListResponse;
import com.codemoa.project.domain.community.service.CommunityBoardService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(BoardViewController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("BoardViewController Thymeleaf 렌더 스모크")
class BoardViewControllerWebMvcTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	CommunityBoardService communityBoardService;

	@Test
	@DisplayName("GET /community/free — 목록 템플릿 200")
	void boardList_renders() throws Exception {
		Page<BoardListResponse> page = new PageImpl<>(List.of(), PageRequest.of(0, 10), 0);
		given(communityBoardService.findAll(anyString(), anyString(), anyString(), anyString(), any()))
				.willReturn(page);

		mockMvc.perform(get("/community/free"))
				.andExpect(status().isOk())
				.andExpect(view().name("views/community/boardList"));
	}

	@Test
	@DisplayName("서비스 IllegalArgumentException 시 레이아웃 있는 error/404 (404)")
	void boardList_serviceNotFound_rendersError404() throws Exception {
		willThrow(new IllegalArgumentException("게시글을 찾을 수 없습니다."))
				.given(communityBoardService).findAll(anyString(), anyString(), anyString(), anyString(), any());

		mockMvc.perform(get("/community/free"))
				.andExpect(status().isNotFound())
				.andExpect(view().name("error/404"));
	}
}
