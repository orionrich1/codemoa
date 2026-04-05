package com.codemoa.project.domain.information.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemoa.project.common.service.FileStorageService;
import com.codemoa.project.domain.information.entity.Contest;
import com.codemoa.project.domain.information.service.InformationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 2-3: InformationController에서 공모전(Contest) 관련 핸들러만 추출.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class ContestController {

	private static final String SUB_DIR = "information";

	private final InformationService informationService;
	private final FileStorageService fileStorageService;

	@GetMapping("/information/contest")
	public String contestList(Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "type", defaultValue = "null") String type,
			@RequestParam(value = "keyword", defaultValue = "null") String keyword,
			@RequestParam(value = "order", defaultValue = "null") String order) {
		model.addAllAttributes(informationService.contestList(pageNum, type, keyword, 8, 10, order));
		return "views/information/informationList2";
	}

	@GetMapping("/information/contestDetail")
	public String contestDetail(Model model,
			@RequestParam("no") int no,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "type", defaultValue = "null") String type,
			@RequestParam(value = "keyword", defaultValue = "null") String keyword,
			@RequestParam(value = "order", defaultValue = "null") String order) {
		model.addAttribute("pageNum", pageNum);
		model.addAttribute(informationService.getContest(no));
		model.addAttribute("order", order);
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		return "views/information/informationContestDetail";
	}

	@GetMapping("/information/contestAdd")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String contestAddForm() {
		return "views/information/informationContestWriteForm";
	}

	@PostMapping("/information/contestWrite")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String addContest(Contest contest,
			@RequestParam(value = "addFile", required = false) MultipartFile multipartFile,
			@RequestParam("start") String start,
			@RequestParam("end") String end) throws IOException {
		contest.setStartDate(Timestamp.valueOf(LocalDateTime.parse(start)));
		contest.setEndDate(Timestamp.valueOf(LocalDateTime.parse(end)));
		String savedFileName = fileStorageService.store(multipartFile, SUB_DIR);
		if (savedFileName != null) contest.setFile1(savedFileName);
		informationService.addContest(contest);
		return "redirect:/information/contest";
	}

	@PostMapping("/information/contestUpdateForm")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String contestUpdateForm(Model model,
			@RequestParam("no") int no,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		model.addAttribute("contest", informationService.getContest(no));
		model.addAttribute("pageNum", pageNum);
		return "views/information/informationContestUpdateForm";
	}

	@PostMapping("/information/contestUpdate")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String updateContest(Contest contest, RedirectAttributes reAttrs,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "addFile", required = false) MultipartFile multipartFile,
			@RequestParam("start") String start,
			@RequestParam("end") String end) throws IOException {
		contest.setStartDate(Timestamp.valueOf(LocalDateTime.parse(start)));
		contest.setEndDate(Timestamp.valueOf(LocalDateTime.parse(end)));
		if (multipartFile != null && !multipartFile.isEmpty()) {
			String existing = informationService.getContest(contest.getContestNo()).getFile1();
			fileStorageService.delete(SUB_DIR, existing);
			String savedFileName = fileStorageService.store(multipartFile, SUB_DIR);
			if (savedFileName != null) contest.setFile1(savedFileName);
		} else {
			contest.setFile1(informationService.getContest(contest.getContestNo()).getFile1());
		}
		informationService.updateContest(contest);
		reAttrs.addAttribute("pageNum", pageNum);
		return "redirect:/information/contest";
	}

	@PostMapping("/information/contestDelete")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteContest(@RequestParam("no") int no, RedirectAttributes reAttrs,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "type", defaultValue = "null") String type,
			@RequestParam(value = "keyword", defaultValue = "null") String keyword) {
		Contest contest = informationService.getContest(no);
		if (contest != null) fileStorageService.delete(SUB_DIR, contest.getFile1());
		informationService.deleteContest(no);

		boolean searchOption = !type.equals("null") && !keyword.equals("null");
		if (searchOption) {
			reAttrs.addAttribute("type", type);
			reAttrs.addAttribute("keyword", keyword);
		}
		reAttrs.addAttribute("pageNum", pageNum);
		return "redirect:/information/contest";
	}
}
