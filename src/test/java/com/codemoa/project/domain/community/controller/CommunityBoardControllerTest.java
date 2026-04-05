package com.codemoa.project.domain.community.controller;

import com.codemoa.project.domain.community.service.CommunityBoardService;
import com.codemoa.project.support.annotation.ApiControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommunityBoardController.class)
@DisplayName("CommunityBoardController 슬라이스 테스트")
class CommunityBoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommunityBoardService communityBoardService;

    @Nested
    @DisplayName("GET /api/boards — 게시글 목록 조회")
    class GetBoardList {

        @Test
        @WithMockUser
        @DisplayName("정상 조회 시 200을 반환한다")
        void 정상_조회_시_200_반환() throws Exception {
            given(communityBoardService.findAll(anyString(), anyString(), anyString(), any()))
                    .willReturn(Page.empty());

            mockMvc.perform(get("/api/boards")
                            .param("category", "Java")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("GET /api/boards/{boardNo} — 게시글 단건 조회")
    class GetBoard {

        @Test
        @WithMockUser
        @DisplayName("존재하지 않는 게시글 조회 시 예외를 서비스에 위임한다")
        void 존재하지_않는_게시글_서비스에_위임() throws Exception {
            willThrow(new IllegalArgumentException("게시글을 찾을 수 없습니다."))
                    .given(communityBoardService).findById(9999);

            mockMvc.perform(get("/api/boards/9999"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("DELETE /api/boards/{boardNo} — 게시글 삭제")
    class DeleteBoard {

        @Test
        @WithMockUser(username = "user01")
        @DisplayName("정상 삭제 시 200을 반환하고 서비스를 호출한다")
        void 정상_삭제_시_200_반환() throws Exception {
            mockMvc.perform(delete("/api/boards/1"))
                    .andExpect(status().isOk());

            verify(communityBoardService).delete(1, "user01");
        }

        @Test
        @WithMockUser(username = "hacker")
        @DisplayName("권한 없는 삭제 시 서비스 예외가 403으로 응답된다")
        void 권한없는_삭제_시_403_반환() throws Exception {
            willThrow(new IllegalStateException("삭제 권한이 없습니다."))
                    .given(communityBoardService).delete(1, "hacker");

            mockMvc.perform(delete("/api/boards/1"))
                    .andExpect(status().isForbidden());
        }
    }
}
