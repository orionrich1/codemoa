// BoardViewController.java
package com.codemoa.project.domain.community.controller;

import com.codemoa.project.domain.community.dto.response.BoardDetailResponse;
import com.codemoa.project.domain.community.dto.response.BoardListResponse;
import com.codemoa.project.domain.community.service.CommunityBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BoardViewController {

    private final CommunityBoardService communityBoardService;

    @GetMapping("/list")
    public String redirectToList() {
        return "redirect:/community/free";
    }

    /**
     * [핵심 수정] @PathVariable에 이름을 명시하여 컴파일러 설정과 무관하게 동작하도록 수정했습니다.
     */
    @GetMapping("/community/{category}")
    public String boardList(@PathVariable("category") String category,
                            Model model,
                            @RequestParam(value = "searchType", defaultValue = "title_content") String searchType,
                            @RequestParam(value = "keyword", defaultValue = "") String keyword,
                            @PageableDefault(size = 10) Pageable pageable) {

        Page<BoardListResponse> boardPage = communityBoardService.findAll(category, searchType, keyword, pageable);

        int nowPage = boardPage.getPageable().getPageNumber() + 1;
        int startPage = (int) (Math.floor((nowPage - 1) / 10.0) * 10) + 1;
        int endPage = Math.min(startPage + 9, boardPage.getTotalPages());
        if (boardPage.getTotalPages() == 0) endPage = 1;

        Map<String, String> categoryNames = Map.of(
            "free", "자유 게시판", "Java", "Java 게시판", "Python", "Python 게시판",
            "JavaScript", "JavaScript 게시판", "C#", "C# 게시판", "Kotlin", "Kotlin 게시판", "etc", "기타 언어 게시판"
        );
        model.addAttribute("categoryName", categoryNames.getOrDefault(category, "전체 게시판"));

        model.addAttribute("boards", boardPage);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("category", category);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "views/community/boardList";
    }

    /**
     * [핵심 수정] @PathVariable에 이름을 명시하여 컴파일러 설정과 무관하게 동작하도록 수정했습니다.
     */
    @GetMapping("/boards/{boardNo}")
    public String boardDetail(@PathVariable("boardNo") Integer boardNo, Model model) {
        BoardDetailResponse board = communityBoardService.findById(boardNo);
        model.addAttribute("board", board);
        return "views/community/boardDetail";
    }

    @GetMapping("/boards/write")
    public String boardWrite() {
        return "views/community/boardWrite";
    }

    /**
     * [핵심 수정] @PathVariable에 이름을 명시하여 컴파일러 설정과 무관하게 동작하도록 수정했습니다.
     */
    @GetMapping("/boards/{boardNo}/edit")
    public String boardEditForm(@PathVariable("boardNo") Integer boardNo, Model model) {
        BoardDetailResponse board = communityBoardService.findById(boardNo);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();
        if (!board.getAuthorId().equals(currentUserId)) {
            return "redirect:/boards/" + boardNo;
        }
        model.addAttribute("board", board);
        return "views/community/boardUpdate";
    }
}
