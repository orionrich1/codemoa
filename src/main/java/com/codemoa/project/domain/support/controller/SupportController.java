package com.codemoa.project.domain.support.controller;

import com.codemoa.project.domain.support.FaqCategory;
import com.codemoa.project.domain.support.dto.FaqCreateRequest;
import com.codemoa.project.domain.support.dto.QnaCreateRequest;
import com.codemoa.project.domain.support.dto.QnaReplyRequest;
import com.codemoa.project.domain.support.entity.Qna;
import com.codemoa.project.domain.support.service.SupportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/support")
@RequiredArgsConstructor
public class SupportController {

	private final SupportService supportService;

	@GetMapping({"", "/"})
	public String supportHub() {
		return "views/support/support-hub";
	}

	@GetMapping("/faq")
	public String faqList(Model model) {
		model.addAttribute("faqHasAny", supportService.hasAnyFaq());
		model.addAttribute("faqByCategory", supportService.getFaqsGroupedByCategory());
		model.addAttribute("faqCategoryOrder", FaqCategory.orderedLabels());
		if (!model.containsAttribute("faqCreate")) {
			model.addAttribute("faqCreate", new FaqCreateRequest());
		}
		return "views/support/faq-list";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/faq")
	public String createFaq(@Valid @ModelAttribute("faqCreate") FaqCreateRequest faqCreate,
			BindingResult bindingResult,
			Model model) {
		model.addAttribute("faqHasAny", supportService.hasAnyFaq());
		model.addAttribute("faqByCategory", supportService.getFaqsGroupedByCategory());
		model.addAttribute("faqCategoryOrder", FaqCategory.orderedLabels());
		if (!FaqCategory.isValidLabel(faqCreate.getCategory())) {
			bindingResult.rejectValue("category", "invalid.faq.category", "카테고리를 올바르게 선택해 주세요.");
		}
		if (bindingResult.hasErrors()) {
			return "views/support/faq-list";
		}
		supportService.createFaq(faqCreate.toEntity());
		return "redirect:/support/faq";
	}

	@GetMapping("/qna")
	public String qnaList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "type", defaultValue = "title") String type,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "answer", defaultValue = "all") String answer,
			Model model) {

		String qType = normalizeSearchType(type);
		String qKeyword = normalizeKeyword(keyword);
		String answerFilter = normalizeAnswerFilter(answer);

		int pageSize = 10;
		int safePage = Math.max(1, page);
		Map<String, Object> result = supportService.getQnaList(safePage, pageSize, qType, qKeyword, answerFilter);

		model.addAttribute("qnaList", result.get("list"));
		model.addAttribute("currentPage", safePage);
		model.addAttribute("searchType", qType != null ? qType : "title");
		model.addAttribute("keyword", qKeyword != null ? qKeyword : "");
		model.addAttribute("answerFilter", answerFilter != null ? answerFilter : "all");

		int totalCount = (int) result.get("totalCount");
		int totalPages = totalCount == 0 ? 0 : (int) Math.ceil((double) totalCount / pageSize);
		model.addAttribute("totalPages", totalPages);

		return "views/support/qna-list";
	}

	@GetMapping("/qna/{qnaId}")
	public String qnaDetail(@PathVariable("qnaId") Long qnaId, Model model, RedirectAttributes redirectAttrs) {
		try {
			Qna qna = supportService.getQnaWithReplies(qnaId);
			model.addAttribute("qna", qna);
			model.addAttribute("replyList", supportService.findRepliesByQnaId(qnaId));
			QnaReplyRequest replyForm = new QnaReplyRequest();
			replyForm.setQnaId(qnaId);
			model.addAttribute("qnaReply", replyForm);
			return "views/support/qna-detail";
		} catch (IllegalArgumentException e) {
			redirectAttrs.addFlashAttribute("errorMsg", "요청하신 질문을 찾을 수 없습니다.");
			return "redirect:/support/qna";
		}
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/qna/write")
	public String qnaWriteForm(Model model) {
		if (!model.containsAttribute("qnaForm")) {
			model.addAttribute("qnaForm", new QnaCreateRequest());
		}
		return "views/support/qna-form";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/qna/write")
	public String createQna(@Valid @ModelAttribute("qnaForm") QnaCreateRequest qnaForm,
			BindingResult bindingResult,
			Principal principal) {
		if (bindingResult.hasErrors()) {
			return "views/support/qna-form";
		}
		supportService.createQna(qnaForm.toEntity(principal.getName()));
		return "redirect:/support/qna";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/qna/{qnaId}/edit")
	public String qnaEditForm(@PathVariable("qnaId") Long qnaId, Model model, Principal principal,
			RedirectAttributes redirectAttrs) {
		try {
			Qna qna = supportService.getQnaForAuthorEdit(qnaId, principal.getName());
			if (!model.containsAttribute("qnaForm")) {
				QnaCreateRequest form = new QnaCreateRequest();
				form.setTitle(qna.getTitle());
				form.setContent(qna.getContent());
				model.addAttribute("qnaForm", form);
			}
			model.addAttribute("editQnaId", qnaId);
			return "views/support/qna-edit";
		} catch (IllegalArgumentException e) {
			redirectAttrs.addFlashAttribute("errorMsg", "요청하신 질문을 찾을 수 없습니다.");
			return "redirect:/support/qna";
		} catch (IllegalStateException e) {
			redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
			return "redirect:/support/qna/" + qnaId;
		}
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/qna/{qnaId}/edit")
	public String updateQna(@PathVariable("qnaId") Long qnaId,
			@Valid @ModelAttribute("qnaForm") QnaCreateRequest qnaForm,
			BindingResult bindingResult,
			Principal principal,
			Model model,
			RedirectAttributes redirectAttrs) {
		model.addAttribute("editQnaId", qnaId);
		if (bindingResult.hasErrors()) {
			return "views/support/qna-edit";
		}
		try {
			supportService.updateQnaByAuthor(qnaId, qnaForm, principal.getName());
		} catch (IllegalArgumentException e) {
			redirectAttrs.addFlashAttribute("errorMsg", "요청하신 질문을 찾을 수 없습니다.");
			return "redirect:/support/qna";
		} catch (IllegalStateException e) {
			redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
			return "redirect:/support/qna/" + qnaId;
		}
		return "redirect:/support/qna/" + qnaId;
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/qna/{qnaId}/delete")
	public String deleteQnaByAuthor(@PathVariable("qnaId") Long qnaId, Principal principal,
			RedirectAttributes redirectAttrs) {
		try {
			supportService.deleteQnaByAuthor(qnaId, principal.getName());
		} catch (IllegalArgumentException e) {
			redirectAttrs.addFlashAttribute("errorMsg", "요청하신 질문을 찾을 수 없습니다.");
			return "redirect:/support/qna";
		} catch (IllegalStateException e) {
			redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
			return "redirect:/support/qna/" + qnaId;
		}
		return "redirect:/support/qna";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/qna/reply")
	public String createReply(@Valid @ModelAttribute("qnaReply") QnaReplyRequest qnaReply,
			BindingResult bindingResult,
			Principal principal,
			Model model,
			RedirectAttributes redirectAttrs) {
		Long qnaId = qnaReply.getQnaId();
		if (bindingResult.hasErrors()) {
			redirectAttrs.addFlashAttribute("replyValidationFailed", true);
			if (qnaId != null) {
				return "redirect:/support/qna/" + qnaId;
			}
			return "redirect:/support/qna";
		}
		try {
			Qna qna = supportService.getQnaById(qnaId);
			if (qna.isAnswered()) {
				redirectAttrs.addFlashAttribute("errorMsg", "이미 답변이 완료된 질문입니다.");
				return "redirect:/support/qna/" + qnaId;
			}
			supportService.createReply(qnaReply.toEntity(principal.getName()));
		} catch (IllegalArgumentException e) {
			redirectAttrs.addFlashAttribute("errorMsg", "질문을 찾을 수 없습니다.");
			return "redirect:/support/qna";
		}
		return "redirect:/support/qna/" + qnaId;
	}

	private static String normalizeKeyword(String keyword) {
		if (keyword == null) {
			return null;
		}
		String k = keyword.trim();
		if (k.isEmpty() || "null".equalsIgnoreCase(k)) {
			return null;
		}
		return k;
	}

	private static String normalizeSearchType(String type) {
		if (type == null || type.isBlank()) {
			return "title";
		}
		String t = type.trim();
		if ("title".equals(t) || "writer".equals(t) || "content".equals(t)) {
			return t;
		}
		return "title";
	}

	/** {@code null} = DB 필터 없음(전체). waiting / answered 만 허용. */
	private static String normalizeAnswerFilter(String answer) {
		if (answer == null || answer.isBlank() || "all".equalsIgnoreCase(answer.trim())) {
			return null;
		}
		String a = answer.trim().toLowerCase();
		if ("waiting".equals(a) || "answered".equals(a)) {
			return a;
		}
		return null;
	}
}
