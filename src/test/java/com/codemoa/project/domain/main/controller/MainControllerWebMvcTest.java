package com.codemoa.project.domain.main.controller;

import com.codemoa.project.domain.chat.service.ChatService;
import com.codemoa.project.domain.community.service.CommunityBoardService;
import com.codemoa.project.domain.diary.service.DiaryService;
import com.codemoa.project.domain.employment.service.EmploymentService;
import com.codemoa.project.domain.information.service.InformationService;
import com.codemoa.project.domain.main.service.MainService;
import com.codemoa.project.domain.problem.service.ProblemService;
import com.codemoa.project.domain.ranking.service.RankingService;
import com.codemoa.project.domain.recruit.service.TeamRecruitService;
import com.codemoa.project.domain.support.service.SupportService;
import com.codemoa.project.domain.user.service.UserService;
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
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(MainController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("MainController Thymeleaf 렌더 스모크")
class MainControllerWebMvcTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	MainService mainService;
	@MockBean
	CommunityBoardService communityBoardService;
	@MockBean
	InformationService informationService;
	@MockBean
	TeamRecruitService teamRecruitService;
	@MockBean
	RankingService rankingService;
	@MockBean
	EmploymentService employmentService;
	@MockBean
	SupportService supportService;
	@MockBean
	ProblemService problemService;
	@MockBean
	DiaryService diaryService;
	@MockBean
	ChatService chatService;
	@MockBean
	UserService userService;

	@Test
	@DisplayName("GET / — 메인 템플릿 200")
	void mainPage_renders() throws Exception {
		stubMainPage();
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("views/main/index"));
	}

	@Test
	@DisplayName("GET /search — 검색 템플릿 200")
	void searchPage_renders() throws Exception {
		Map<String, Object> empty = new HashMap<>();
		empty.put("searchList", List.of());
		empty.put("searchCount", 0);
		empty.put("sort", "recent");
		empty.put("typeFilter", "all");
		empty.put("emptyKeyword", Boolean.TRUE);
		given(mainService.searchAll(anyString(), anyString(), anyString())).willReturn(empty);

		mockMvc.perform(get("/search"))
				.andExpect(status().isOk())
				.andExpect(view().name("views/main/search"));
	}

	private void stubMainPage() {
		given(communityBoardService.getLatestPostsByCategory(anyString(), anyInt())).willReturn(List.of());
		given(communityBoardService.getPopularPosts(anyInt())).willReturn(List.of());
		given(communityBoardService.getLatestPostsAllCategories(anyInt())).willReturn(List.of());
		given(rankingService.getWeeklyTopForPublic(anyInt())).willReturn(List.of());
		given(informationService.findLatestLectures(anyInt())).willReturn(List.of());
		given(informationService.findLatestBooks(anyInt())).willReturn(List.of());
		given(informationService.findContestsForMainHub(anyInt(), anyInt())).willReturn(List.of());
		given(teamRecruitService.getLatestRecruits(anyInt())).willReturn(List.of());
		given(employmentService.findLatestEmployments(anyInt())).willReturn(List.of());
		given(employmentService.countAllEmployments()).willReturn(0L);
		given(informationService.countContestsEndingWithinDays(anyInt())).willReturn(0);
		given(supportService.countUnansweredQna()).willReturn(0);
		given(supportService.findLatestQnaForMain(anyInt())).willReturn(List.of());
		given(problemService.getDailyProblem()).willReturn(null);
		given(diaryService.findLatestProjectsForMain(nullable(String.class), anyInt())).willReturn(List.of());
		given(userService.countAllUsers()).willReturn(0L);
		given(communityBoardService.countAllPosts()).willReturn(0L);
		given(problemService.countProblems()).willReturn(0);
		given(informationService.countAllContests()).willReturn(0);
		given(rankingService.countActiveUsersThisWeek()).willReturn(0L);
		given(chatService.findAllRooms()).willReturn(List.of());
	}
}
