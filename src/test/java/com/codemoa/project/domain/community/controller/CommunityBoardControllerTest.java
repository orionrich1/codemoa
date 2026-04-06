package com.codemoa.project.domain.community.controller;

import com.codemoa.project.domain.community.service.CommunityBoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * JUnit {@code @Nested} 내부 클래스는 별도 테스트 클래스로 인식되어 {@link WebMvcTest}가 적용되지 않고
 * 전체 애플리케이션 컨텍스트가 기동되는 문제가 있어, 평면 구조로 유지한다.
 */
@WebMvcTest(CommunityBoardController.class)
@DisplayName("CommunityBoardController 슬라이스 테스트")
class CommunityBoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommunityBoardService communityBoardService;

    @Test
    @WithMockUser
    @DisplayName("GET /api/boards — 정상 조회 시 200")
    void getBoardList_ok() throws Exception {
        given(communityBoardService.findAll(anyString(), anyString(), anyString(), anyString(), any()))
                .willReturn(Page.empty());

        mockMvc.perform(get("/api/boards")
                        .param("category", "Java")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @DisplayName("GET /api/boards/{id} — 없는 글은 서비스 예외 → 404")
    void getBoard_notFound() throws Exception {
        willThrow(new IllegalArgumentException("게시글을 찾을 수 없습니다."))
                .given(communityBoardService).findById(9999);

        mockMvc.perform(get("/api/boards/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user01")
    @DisplayName("DELETE /api/boards/{id} — 정상 삭제 200")
    void deleteBoard_ok() throws Exception {
        mockMvc.perform(delete("/api/boards/1").with(csrf()))
                .andExpect(status().isOk());

        verify(communityBoardService).delete(1, "user01");
    }

    @Test
    @WithMockUser(username = "hacker")
    @DisplayName("DELETE /api/boards/{id} — 권한 없음 403")
    void deleteBoard_forbidden() throws Exception {
        willThrow(new IllegalStateException("삭제 권한이 없습니다."))
                .given(communityBoardService).delete(1, "hacker");

        mockMvc.perform(delete("/api/boards/1").with(csrf()))
                .andExpect(status().isForbidden());
    }
}
