package com.codemoa.project.domain.ranking.controller;

import com.codemoa.project.domain.ranking.dto.response.RankingPageResponse;
import com.codemoa.project.domain.ranking.service.RankingService;
import com.codemoa.project.domain.user.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping("/ranking")
    public String showRankingPage(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        
        // 비로그인 사용자를 위해 기본값 설정
        String currentUserId = null;
        if (userDetails != null) {
            currentUserId = userDetails.getUsername();
        }
        
        // 서비스 호출하여 랭킹 페이지에 필요한 모든 데이터 조회
        RankingPageResponse rankingData = rankingService.getRankingPageData(currentUserId);
        
        // Model에 데이터 추가하여 View로 전달
        model.addAttribute("rankingData", rankingData);
        
        // 랭킹을 표시할 Thymeleaf 템플릿 경로 반환
        return "views/ranking/rankingList";
    }
}