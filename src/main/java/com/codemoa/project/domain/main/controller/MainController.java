package com.codemoa.project.domain.main.controller;

import com.codemoa.project.domain.chat.dto.ChatRoomDto;
import com.codemoa.project.domain.chat.service.ChatService;
import com.codemoa.project.domain.community.entity.CommunityBoard;
import com.codemoa.project.domain.community.service.CommunityBoardService;
import com.codemoa.project.domain.diary.entity.Project;
import com.codemoa.project.domain.diary.service.DiaryService;
import com.codemoa.project.domain.employment.dto.response.EmploymentDto;
import com.codemoa.project.domain.employment.service.EmploymentService;
import com.codemoa.project.domain.information.entity.Book;
import com.codemoa.project.domain.information.entity.Contest;
import com.codemoa.project.domain.information.entity.Lecture;
import com.codemoa.project.domain.information.service.InformationService;
import com.codemoa.project.domain.main.service.MainService;
import com.codemoa.project.domain.main.support.CommunityPostPreview;
import com.codemoa.project.domain.main.support.MainPageDemoData;
import com.codemoa.project.domain.problem.entity.Problem;
import com.codemoa.project.domain.problem.service.ProblemService;
import com.codemoa.project.domain.ranking.dto.response.UserRankingResponse;
import com.codemoa.project.domain.ranking.service.RankingService;
import com.codemoa.project.domain.recruit.entity.TeamRecruit;
import com.codemoa.project.domain.recruit.service.TeamRecruitService;
import com.codemoa.project.domain.support.entity.Qna;
import com.codemoa.project.domain.support.service.SupportService;
import com.codemoa.project.domain.user.security.CustomUserDetails;
import com.codemoa.project.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final MainService mainService;
	private final CommunityBoardService communityBoardService;
	private final InformationService informationService;
	private final TeamRecruitService teamRecruitService;
	private final RankingService rankingService;
	private final EmploymentService employmentService;
	private final SupportService supportService;
	private final ProblemService problemService;
	private final DiaryService diaryService;
	private final ChatService chatService;
	private final UserService userService;

	private static final int CATEGORY_POST_LIMIT = 8;
	private static final int PORTAL_FEED_LIMIT = 8;
	private static final int RANKING_WIDGET_LIMIT = 5;
	private static final int HUB_COLUMN_LIMIT = 3;
	private static final int JOBS_TEAM_LIMIT = 5;
	private static final int CONTEST_ENDING_SOON_DAYS = 14;
	private static final int QNA_HIGHLIGHT_LIMIT = 4;
	private static final int DIARY_WIDGET_LIMIT = 4;
	private static final int CHAT_ROOM_WIDGET_LIMIT = 3;

	@GetMapping("/")
	public String mainPage(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		putCategoryPosts(model, "freePosts", "자유");
		putCategoryPosts(model, "javaPosts", "Java");
		putCategoryPosts(model, "jsPosts", "JavaScript");
		putCategoryPosts(model, "pythonPosts", "Python");
		putCategoryPosts(model, "kotlinPosts", "Kotlin");

		List<CommunityBoard> popularRaw = communityBoardService.getPopularPosts(PORTAL_FEED_LIMIT);
		List<CommunityBoard> latestRaw = communityBoardService.getLatestPostsAllCategories(PORTAL_FEED_LIMIT);
		model.addAttribute("mainShowDemoNotice", popularRaw.isEmpty() && latestRaw.isEmpty());

		// Q-7: 음수 ID를 가진 더미 데이터는 존재하지 않는 링크를 노출하므로 사용 중단.
		//      DB가 비어 있으면 빈 리스트를 내려보내고 템플릿이 빈 상태를 표시.
		model.addAttribute("popularPosts", new ArrayList<CommunityPostPreview>(popularRaw));
		model.addAttribute("latestPostsAll", new ArrayList<CommunityPostPreview>(latestRaw));

		List<UserRankingResponse> weekly = rankingService.getWeeklyTopForPublic(RANKING_WIDGET_LIMIT);
		model.addAttribute("weeklyRanking", weekly);

		List<Lecture> hubLectures = informationService.findLatestLectures(HUB_COLUMN_LIMIT);
		model.addAttribute("hubLectures", hubLectures);

		List<Book> hubBooks = informationService.findLatestBooks(HUB_COLUMN_LIMIT);
		model.addAttribute("hubBooks", hubBooks);

		List<Contest> hubContests = informationService.findContestsForMainHub(HUB_COLUMN_LIMIT, CONTEST_ENDING_SOON_DAYS);
		model.addAttribute("hubContests", hubContests);

		List<EmploymentDto> jobs = employmentService.findLatestEmployments(JOBS_TEAM_LIMIT);
		model.addAttribute("latestEmployments", jobs);

		List<TeamRecruit> teams = teamRecruitService.getLatestRecruits(JOBS_TEAM_LIMIT);
		model.addAttribute("latestTeamRecruits", teams);

		model.addAttribute("ribbonEmploymentTotal", employmentService.countAllEmployments());
		model.addAttribute("ribbonContestEndingSoonCount",
				informationService.countContestsEndingWithinDays(CONTEST_ENDING_SOON_DAYS));
		model.addAttribute("ribbonQnaUnansweredCount", supportService.countUnansweredQna());

		// Q-7: Q&A 음수 ID 더미 데이터 링크 제거 — 빈 리스트로 대체
		List<Qna> highlightQnas = supportService.findLatestQnaForMain(QNA_HIGHLIGHT_LIMIT);
		model.addAttribute("highlightQnas", highlightQnas);

		// P1: 오늘의 AI 문제 위젯
		Problem dailyProblem = problemService.getDailyProblem();
		model.addAttribute("dailyProblem", dailyProblem);

		// P1: 프로젝트 다이어리 섹션 — 본인 프로젝트만 (비로그인 시 빈 목록)
		String diaryUserId = userDetails != null ? userDetails.getUsername() : null;
		List<Project> latestProjects = diaryService.findLatestProjectsForMain(diaryUserId, DIARY_WIDGET_LIMIT);
		model.addAttribute("latestProjects", latestProjects);

		// P1: 플랫폼 신뢰 통계 바
		model.addAttribute("statsUserCount", userService.countAllUsers());
		model.addAttribute("statsTotalPosts", communityBoardService.countAllPosts());
		model.addAttribute("statsTotalProblems", problemService.countProblems());
		model.addAttribute("statsContestCount", informationService.countAllContests());
		model.addAttribute("statsActiveUsersThisWeek", rankingService.countActiveUsersThisWeek());

		// P3: 로그인 사용자 개인화 요약 카드
		if (userDetails != null) {
			String userId = userDetails.getUsername();
			long weeklyPoints = rankingService.getWeeklyPointsByUser(userId);
			int myUnansweredQna = supportService.countMyUnansweredQna(userId);
			model.addAttribute("personalWeeklyPoints", weeklyPoints);
			model.addAttribute("personalMyUnansweredQna", myUnansweredQna);
		}

		// P2: 채팅방 위젯
		List<ChatRoomDto> chatRooms = chatService.findAllRooms();
		model.addAttribute("sidebarChatRooms",
				chatRooms.size() > CHAT_ROOM_WIDGET_LIMIT ? chatRooms.subList(0, CHAT_ROOM_WIDGET_LIMIT) : chatRooms);

		return "views/main/index";
	}

	private void putCategoryPosts(Model model, String attributeName, String category) {
		List<CommunityBoard> raw = communityBoardService.getLatestPostsByCategory(category, CATEGORY_POST_LIMIT);
		// Q-7: 음수 ID 더미 데이터 제거 — DB가 비어 있으면 빈 리스트 사용
		model.addAttribute(attributeName, new ArrayList<CommunityPostPreview>(raw));
	}

	@GetMapping("/search")
	public String search(
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "sort", required = false, defaultValue = "recent") String sort,
			@RequestParam(value = "type", required = false, defaultValue = "all") String typeFilter,
			Model model) {
		Map<String, Object> map = mainService.searchAll(keyword, sort, typeFilter);
		model.addAllAttributes(map);
		model.addAttribute("keyword", keyword);
		return "views/main/search";
	}
}
