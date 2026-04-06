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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class BoardViewController {

    private static final Map<String, String> COMMUNITY_CATEGORY_TITLES = Map.of(
            "free", "자유 게시판", "Java", "Java 게시판", "Python", "Python 게시판",
            "JavaScript", "JavaScript 게시판", "C#", "C# 게시판", "Kotlin", "Kotlin 게시판", "etc", "기타 언어 게시판");

    private final CommunityBoardService communityBoardService;

    private static String listPageTitle(String pathSegment) {
        return COMMUNITY_CATEGORY_TITLES.getOrDefault(pathSegment, "전체 게시판");
    }

    private static String listPageTitleFromBoard(String urlSegment, String dbCategory) {
        return COMMUNITY_CATEGORY_TITLES.getOrDefault(urlSegment, dbCategory + " 게시판");
    }

    @GetMapping("/{category}")
    public String boardList(@PathVariable("category") String category,
            Model model,
            @RequestParam(value = "searchType", defaultValue = "title_content") String searchType,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "sort", defaultValue = "board") String sort,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<BoardListResponse> boardPage = communityBoardService.findAll(category, searchType, keyword, sort, pageable);

        int nowPage = boardPage.getPageable().getPageNumber() + 1;
        int startPage = (int) (Math.floor((nowPage - 1) / 10.0) * 10) + 1;
        int endPage = Math.min(startPage + 9, boardPage.getTotalPages());
        if (boardPage.getTotalPages() == 0) {
            endPage = 1;
        }

        model.addAttribute("categoryName", listPageTitle(category));
        model.addAttribute("boards", boardPage);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("category", category);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sort", sort);

        return "views/community/boardList";
    }

    @GetMapping("/{category}/{boardNo}")
    public String boardDetail(@PathVariable("category") String category, @PathVariable("boardNo") Integer boardNo,
            Model model) {
        BoardDetailResponse board = communityBoardService.findById(boardNo);
        model.addAttribute("board", board);
        model.addAttribute("boardListTitle", listPageTitleFromBoard(board.getCategoryUrlSegment(), board.getCategory()));
        return "views/community/boardDetail";
    }

    @GetMapping("/{category}/write")
    public String boardWrite(@PathVariable("category") String category, Model model) {
        model.addAttribute("selectedCategory", category);
        String title = listPageTitle(category);
        model.addAttribute("boardListTitle", title);
        model.addAttribute("categoryName", title);
        model.addAttribute("listUrlSegment", category);
        return "views/community/boardWrite";
    }

    @GetMapping("/{category}/{boardNo}/edit")
    public String boardEditForm(@PathVariable("category") String category, @PathVariable("boardNo") Integer boardNo,
            Model model) {
        BoardDetailResponse board = communityBoardService.findById(boardNo);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();
        if (!board.getAuthorId().equals(currentUserId)) {
            return "redirect:/community/" + category + "/" + boardNo;
        }
        model.addAttribute("board", board);
        model.addAttribute("boardListTitle", listPageTitleFromBoard(board.getCategoryUrlSegment(), board.getCategory()));
        model.addAttribute("categoryName",
                listPageTitleFromBoard(board.getCategoryUrlSegment(), board.getCategory()));
        return "views/community/boardUpdate";
    }
}
