package com.codemoa.project.domain.information.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.codemoa.project.domain.information.service.InformationService;

import lombok.RequiredArgsConstructor;

/**
 * IT 정보 허브(강좌·도서·공모전 진입) 및 향후 공통 정보 기능.
 */
@Controller
@RequiredArgsConstructor
public class InformationController {

	private static final int HUB_PREVIEW_LIMIT = 4;
	private static final int ENDING_SOON_DAYS = 14;

	private final InformationService informationService;

	@GetMapping("/information")
	public String informationHub(Model model) {
		model.addAttribute("hubLectures", informationService.findLatestLectures(HUB_PREVIEW_LIMIT));
		model.addAttribute("hubBooks", informationService.findLatestBooks(HUB_PREVIEW_LIMIT));
		model.addAttribute("hubContests", informationService.findContestsForMainHub(HUB_PREVIEW_LIMIT, ENDING_SOON_DAYS));
		model.addAttribute("contestsEndingSoonCount", informationService.countContestsEndingWithinDays(ENDING_SOON_DAYS));
		return "views/information/informationHub";
	}
}
