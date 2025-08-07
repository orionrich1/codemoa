package com.codemoa.project.domain.support.controller;

import com.codemoa.project.domain.support.dto.QnaCreateRequest;
import com.codemoa.project.domain.support.dto.QnaReplyRequest;
import com.codemoa.project.domain.support.entity.Faq;
import com.codemoa.project.domain.support.entity.Qna;
import com.codemoa.project.domain.support.mapper.SupportMapper;
import com.codemoa.project.domain.support.service.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/support")
@RequiredArgsConstructor
public class SupportController {

    private final SupportService supportService;
    private final SupportMapper supportMapper;

    // FAQ 목록 페이지
    @GetMapping("/faq")
    public String faqList(Model model) {
        List<Faq> faqList = supportService.getAllFaqs();
        model.addAttribute("faqList", faqList);
        return "views/support/faq-list";
    }

    // FAQ 생성 처리
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/faq")
    // ▼▼▼ [수정 1] 파라미터 이름 명시 ▼▼▼
    public String createFaq(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        Faq newFaq = new Faq();
        newFaq.setQuestion(question);
        newFaq.setAnswer(answer);
        supportService.createFaq(newFaq);
        return "redirect:/support/faq";
    }

    // Q&A 목록 페이지
    @GetMapping("/qna")
    // ▼▼▼ [수정 2] 안정성을 위해 파라미터 이름 명시 ▼▼▼
    public String qnaList(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "type", required = false) String type,
                          @RequestParam(value = "keyword", required = false) String keyword,
                          Model model) {
        
        int pageSize = 10; // 한 페이지에 10개씩 표시
        Map<String, Object> result = supportService.getQnaList(page, pageSize, type, keyword);

        model.addAttribute("qnaList", result.get("list"));
        model.addAttribute("currentPage", page);
        
        int totalCount = (int) result.get("totalCount");
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        model.addAttribute("totalPages", totalPages);

        return "views/support/qna-list";
    }

    // Q&A 상세 페이지
    @GetMapping("/qna/{qnaId}")
    // ▼▼▼ [수정 3] 파라미터 이름 명시 ▼▼▼
    public String qnaDetail(@PathVariable("qnaId") Long qnaId, Model model) {
        Qna qna = supportService.getQnaWithReplies(qnaId);
        model.addAttribute("qna", qna);
        model.addAttribute("replyList", supportMapper.findRepliesByQnaId(qnaId));
        return "views/support/qna-detail";
    }

    // Q&A 작성 폼 페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/qna/write")
    public String qnaWriteForm() {
        // qna-form.html 파일이 templates/views/support/ 폴더에 필요합니다.
        return "views/support/qna-form";
    }

    // Q&A 작성 처리
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/qna/write")
    public String createQna(QnaCreateRequest request, Principal principal) {
        String currentUserId = principal.getName();
        supportService.createQna(request.toEntity(currentUserId));
        return "redirect:/support/qna";
    }
    
 // Q&A 답변 작성 처리
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // 관리자만 답변 가능
    @PostMapping("/qna/reply")
    public String createReply(QnaReplyRequest request, Principal principal) {
        String adminId = principal.getName();
        supportService.createReply(request.toEntity(adminId));
        return "redirect:/support/qna/" + request.getQnaId();
    }
}