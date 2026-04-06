package com.codemoa.project.domain.recruit.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codemoa.project.common.service.FileStorageService;
import com.codemoa.project.domain.recruit.entity.TeamRecruit;
import com.codemoa.project.domain.recruit.service.TeamRecruitService;
import com.codemoa.project.domain.user.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 3-3: @Autowired → @RequiredArgsConstructor
// 3-5: PrintWriter + return null 패턴 → RedirectAttributes 으로 교체
// 2-2: 파일 업로드 → FileStorageService 위임
// 1-3: 메서드명 PascalCase → lowerCamelCase
@Slf4j
@Controller
@RequiredArgsConstructor
public class TeamRecruitController {

	private static final String SUB_DIR = "recruit";

	private final TeamRecruitService teamRecruitService;
	private final FileStorageService fileStorageService;

	/** 대소문자 오기입 URL 호환을 위한 리다이렉트 유지 */
	@GetMapping("/TeamRecruitList")
	public String redirectTeamRecruitList() {
		return "redirect:/teamRecruitList";
	}

	@DeleteMapping("/recruit/{recruitId}")
	public ResponseEntity<?> deleteRecruit(@PathVariable("recruitId") int recruitId,
			@AuthenticationPrincipal CustomUserDetails principal) {
		if (principal == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
		}
		TeamRecruit teamRecruit = teamRecruitService.getTeamRecruit(recruitId);
		if (teamRecruit == null || !teamRecruit.getUserId().equals(principal.getUsername())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("삭제 권한이 없습니다.");
		}
		boolean result = teamRecruitService.deleteRecruit(recruitId);
		return result
				? ResponseEntity.ok().body("삭제 완료")
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
	}

	/**
	 * 3-5: PrintWriter + return null → RedirectAttributes.addFlashAttribute 로 교체.
	 */
	@GetMapping("/recruit/updateForm")
	public String showUpdateForm(@RequestParam("recruitId") int recruitId,
			@AuthenticationPrincipal CustomUserDetails principal,
			Model model,
			RedirectAttributes redirectAttrs) {
		if (principal == null) {
			return "redirect:/loginForm";
		}
		if (!teamRecruitService.userIdCheck(recruitId, principal.getUsername())) {
			redirectAttrs.addFlashAttribute("errorMsg", "작성자가 일치하지 않습니다.");
			return "redirect:/teamRecruitList";
		}
		model.addAttribute("teamRecruit", teamRecruitService.getTeamRecruit(recruitId));
		return "views/recruit/teamRecruitUpdateForm";
	}

	@PostMapping("/teamRecruitUpdate")
	public String updateTeamRecruit(@ModelAttribute TeamRecruit teamRecruit,
			@RequestParam(value = "attachmentFile", required = false) MultipartFile attachmentFile,
			RedirectAttributes redirectAttrs) {
		try {
			teamRecruitService.updateTeamRecruit(teamRecruit, attachmentFile);
			redirectAttrs.addFlashAttribute("msg", "수정이 완료되었습니다.");
		} catch (Exception e) {
			log.error("수정 중 오류 발생", e);
			redirectAttrs.addFlashAttribute("errorMsg", "수정 중 오류가 발생했습니다.");
			return "redirect:/recruit/updateForm?recruitId=" + teamRecruit.getRecruitId();
		}
		return "redirect:/teamRecruitDetail?recruitId=" + teamRecruit.getRecruitId();
	}

	@GetMapping("/files/{filename}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String filename) throws IOException {
		Path filePath = Paths.get("src/main/resources/static/files/" + filename);
		Resource resource = new UrlResource(filePath.toUri());
		if (!resource.exists() || !resource.isReadable()) {
			return ResponseEntity.notFound().build();
		}
		String encodedFileName = URLEncoder.encode(resource.getFilename(), StandardCharsets.UTF_8)
				.replaceAll("\\+", "%20");
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"")
				.body(resource);
	}

	/**
	 * 2-2: 파일 저장을 FileStorageService에 위임.
	 */
	@PostMapping("/recruit/write")
	public String writeRecruit(TeamRecruit teamRecruit,
			@RequestParam(value = "attachmentFile", required = false) MultipartFile attachmentFile,
			RedirectAttributes redirectAttrs,
			@AuthenticationPrincipal CustomUserDetails principal) {
		if (principal == null) {
			redirectAttrs.addFlashAttribute("errorMsg", "로그인이 필요합니다.");
			return "redirect:/loginForm";
		}
		teamRecruit.setUserId(principal.getUsername());

		if ("TEAM_JOIN".equals(teamRecruit.getRecruitType())) {
			teamRecruit.setRemainingMembers(0);
			if (teamRecruit.getStatus() == null || teamRecruit.getStatus().isBlank()) {
				teamRecruit.setStatus("CLOSED");
			}
		}

		try {
			String saveName = fileStorageService.store(attachmentFile, SUB_DIR);
			if (saveName != null) teamRecruit.setAttachmentUrl(saveName);
		} catch (IOException e) {
			log.error("파일 업로드 실패", e);
			redirectAttrs.addFlashAttribute("uploadError", "파일 업로드에 실패했습니다.");
		}

		teamRecruitService.addTeamRecruit(teamRecruit);
		redirectAttrs.addFlashAttribute("msg", "등록이 완료되었습니다.");
		return "redirect:/teamRecruitList";
	}

	@GetMapping("/addTeamRecruit")
	public String addTeamRecruitForm(Model model) {
		TeamRecruit recruit = new TeamRecruit();
		recruit.setRecruitType("");
		model.addAttribute("teamRecruit", recruit);
		return "views/recruit/teamRecruitWriteForm";
	}

	@GetMapping("/teamRecruitDetail")
	public String teamRecruitDetail(Model model,
			@RequestParam("recruitId") int recruitId,
			@AuthenticationPrincipal CustomUserDetails principal) {
		teamRecruitService.increaseViewCount(recruitId);
		model.addAttribute("teamRecruit", teamRecruitService.getTeamRecruit(recruitId));
		model.addAttribute("loginId", principal != null ? principal.getUsername() : null);
		return "views/recruit/teamRecruitDetail";
	}

	// 1-3: TeamRecruitList (PascalCase) → teamRecruitList (lowerCamelCase)
	@GetMapping("/teamRecruitList")
	public String teamRecruitList(Model model,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@AuthenticationPrincipal CustomUserDetails principal) {
		if ("null".equalsIgnoreCase(type)) type = "";
		if ("null".equalsIgnoreCase(keyword)) keyword = "";

		model.addAllAttributes(teamRecruitService.teamRecruitList(pageNum, type.trim(), keyword.trim()));
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		model.addAttribute("loginId", principal != null ? principal.getUsername() : null);
		return "views/recruit/teamRecruitList";
	}
}
