package com.codemoa.project.domain.main.controller;

import com.codemoa.project.domain.community.service.CommunityBoardService;
import com.codemoa.project.domain.information.service.InformationService;
import com.codemoa.project.domain.recruit.service.TeamRecruitService;
import com.codemoa.project.domain.main.service.MainService; // 기존 MainService 유지
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final MainService mainService;
	
	// ▼▼▼ [메인 페이지를 위한 서비스 의존성 추가] ▼▼▼
	private final CommunityBoardService communityBoardService;
	private final InformationService informationService;
	// ▲▲▲ [메인 페이지를 위한 서비스 의존성 추가] ▲▲▲

	/**
	 * 메인 페이지를 반환합니다.
	 * 각 서비스에서 최신 데이터를 조회하여 Model에 추가합니다.
	 * @return views/main/index
	 */
	@GetMapping("/")
    public String mainPage(Model model) {
        final int POST_LIMIT = 5;

        model.addAttribute("freePosts", communityBoardService.getLatestPostsByCategory("자유", POST_LIMIT));
        model.addAttribute("javaPosts", communityBoardService.getLatestPostsByCategory("Java", POST_LIMIT));
        model.addAttribute("jsPosts", communityBoardService.getLatestPostsByCategory("JavaScript", POST_LIMIT));
        model.addAttribute("pythonPosts", communityBoardService.getLatestPostsByCategory("Python", POST_LIMIT));

        model.addAttribute("recommendedLectures", informationService.findLatestLectures(POST_LIMIT));

        // 3. 팀원 모집 데이터 조회 로직 제거
        // model.addAttribute("teamRecruits", teamRecruitService.getLatestRecruits(POST_LIMIT));

        return "views/main/index";
    }

	@GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
		Map<String, Object> map = mainService.searchAll(keyword);

		model.addAllAttributes(map);
    	model.addAttribute("keyword", keyword);
    	return "views/main/search";
    }
}