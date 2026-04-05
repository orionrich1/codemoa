package com.codemoa.project.domain.information.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemoa.project.common.service.FileStorageService;
import com.codemoa.project.domain.information.entity.Lecture;
import com.codemoa.project.domain.information.service.InformationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 2-3: InformationController에서 강의(Lecture) 관련 핸들러만 추출.
 * 3-3: @Autowired → @RequiredArgsConstructor
 * 3-4: 수정·삭제 핸들러에 ADMIN 권한 검증 추가
 * 2-2: FileStorageService 공통 서비스 사용
 * 3-5: PrintWriter 파라미터 제거
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class LectureController {

	private static final String SUB_DIR = "information";

	private final InformationService informationService;
	private final FileStorageService fileStorageService;

	@GetMapping("/information/lecture")
	public String lectureList(Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "type", defaultValue = "null") String type,
			@RequestParam(value = "keyword", defaultValue = "null") String keyword,
			@RequestParam(value = "order", defaultValue = "null") String order) {
		model.addAllAttributes(informationService.lectureList(pageNum, type, keyword, 8, 10, order));
		return "views/information/informationList";
	}

	@GetMapping("/information/lectureDetail")
	public String lectureDetail(Model model,
			@RequestParam("no") int no,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "type", defaultValue = "null") String type,
			@RequestParam(value = "keyword", defaultValue = "null") String keyword,
			@RequestParam(value = "order", defaultValue = "null") String order) {
		model.addAttribute("pageNum", pageNum);
		model.addAttribute(informationService.getLecture(no));
		model.addAttribute("order", order);
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		return "views/information/informationLectureDetail";
	}

	@GetMapping("/information/lectureAdd")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String lectureAddForm() {
		return "views/information/informationLectureWriteForm";
	}

	@PostMapping("/information/lectureWrite")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String addLecture(Lecture lecture,
			@RequestParam(value = "addFile", required = false) MultipartFile multipartFile) throws IOException {
		String savedFileName = fileStorageService.store(multipartFile, SUB_DIR);
		if (savedFileName != null) lecture.setFile1(savedFileName);
		informationService.addLecture(lecture);
		return "redirect:/information/lecture";
	}

	// 3-4: 수정 폼 진입에도 ADMIN 권한 필요
	@PostMapping("/information/lectureUpdateForm")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String lectureUpdateForm(Model model,
			@RequestParam("no") int no,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		model.addAttribute("lecture", informationService.getLecture(no));
		model.addAttribute("pageNum", pageNum);
		return "views/information/informationLectureUpdateForm";
	}

	@PostMapping("/information/lectureUpdate")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String updateLecture(Lecture lecture,
			@RequestParam(value = "addFile", required = false) MultipartFile multipartFile) throws IOException {
		if (multipartFile != null && !multipartFile.isEmpty()) {
			String existing = informationService.getLecture(lecture.getRecommendNo()).getFile1();
			fileStorageService.delete(SUB_DIR, existing);
			String savedFileName = fileStorageService.store(multipartFile, SUB_DIR);
			if (savedFileName != null) lecture.setFile1(savedFileName);
		} else {
			lecture.setFile1(informationService.getLecture(lecture.getRecommendNo()).getFile1());
		}
		informationService.updateLecture(lecture);
		return "redirect:/information/lecture";
	}

	@PostMapping("/information/lectureDelete")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String deleteLecture(@RequestParam("no") int no, RedirectAttributes reAttrs,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "type", defaultValue = "null") String type,
			@RequestParam(value = "keyword", defaultValue = "null") String keyword) {
		Lecture lecture = informationService.getLecture(no);
		if (lecture != null) fileStorageService.delete(SUB_DIR, lecture.getFile1());
		informationService.deleteLecture(no);

		boolean searchOption = !type.equals("null") && !keyword.equals("null");
		if (searchOption) {
			reAttrs.addAttribute("type", type);
			reAttrs.addAttribute("keyword", keyword);
		}
		reAttrs.addAttribute("pageNum", pageNum);
		return "redirect:/information/lecture";
	}
}
