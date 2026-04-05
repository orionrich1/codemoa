package com.codemoa.project.domain.user.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codemoa.project.domain.diary.dto.request.CreateChecklistRequest;
import com.codemoa.project.domain.diary.dto.request.SaveDiaryRequest;
import com.codemoa.project.domain.diary.dto.request.UpdateCheckStatusRequest;
import com.codemoa.project.domain.diary.dto.request.UpdateChecklistRequest;
import com.codemoa.project.domain.diary.entity.ProjectChecklist;
import com.codemoa.project.domain.diary.entity.ProjectDiary;
import com.codemoa.project.domain.diary.service.DiaryService;
import com.codemoa.project.domain.user.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-pages")
public class MyPageRestController {

	private final DiaryService diaryService;

	// ======================================
	// 프로젝트 체크리스트 (ProjectChecklist) 관련 기능
	// ======================================

	@PostMapping("/updateCheckBox")
	public ResponseEntity<Void> updateCheck(@RequestBody UpdateCheckStatusRequest request,
			@AuthenticationPrincipal CustomUserDetails principal) {
		// C-9: 체크리스트 소유권 검증
		diaryService.verifyChecklistOwnership(request.getNo(), principal.getUsername());
		diaryService.updateCheck(request);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/addChecklist")
	public ResponseEntity<ProjectChecklist> addChecklist(@RequestBody CreateChecklistRequest request,
			@AuthenticationPrincipal CustomUserDetails principal) {
		// C-9: 대상 프로젝트 소유권 검증
		diaryService.verifyProjectOwnership(request.getProjectId(), principal.getUsername());
		ProjectChecklist data = diaryService.addChecklist(request);
		return ResponseEntity.ok(data);
	}

	@PostMapping("/updateChecklist")
	public ResponseEntity<Void> updateChecklist(@RequestBody UpdateChecklistRequest request,
			@AuthenticationPrincipal CustomUserDetails principal) {
		// C-9: 체크리스트 소유권 검증
		diaryService.verifyChecklistOwnership(request.getChecklistId(), principal.getUsername());
		diaryService.updateChecklist(request);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/deleteChecklist")
	public ResponseEntity<Void> deleteChecklist(@RequestBody Map<String, Integer> map,
			@AuthenticationPrincipal CustomUserDetails principal) {
		int checklistId = map.get("no");
		// C-9: 체크리스트 소유권 검증
		diaryService.verifyChecklistOwnership(checklistId, principal.getUsername());
		diaryService.deleteChecklist(checklistId);
		return ResponseEntity.ok().build();
	}

	// ======================================
	// 프로젝트 다이어리 (ProjectDiary) 관련 기능
	// ======================================

	@PostMapping("/saveDiary")
	public ResponseEntity<ProjectDiary> saveDiary(@RequestBody SaveDiaryRequest request,
			@AuthenticationPrincipal CustomUserDetails principal) {
		if (request.getDiaryId() == 0) {
			// 신규: 프로젝트 소유권 검증
			diaryService.verifyProjectOwnership(request.getProjectId(), principal.getUsername());
		} else {
			// 수정: 다이어리 소유권 검증
			diaryService.verifyDiaryOwnership(request.getDiaryId(), principal.getUsername());
		}
		ProjectDiary data = diaryService.saveDiary(request);
		return ResponseEntity.ok(data);
	}

	@DeleteMapping("/deleteDiary/{diaryId}")
	public ResponseEntity<Void> deleteDiary(@PathVariable("diaryId") String diaryId,
			@AuthenticationPrincipal CustomUserDetails principal) {
		int id = Integer.parseInt(diaryId);
		// C-9: 다이어리 소유권 검증
		diaryService.verifyDiaryOwnership(id, principal.getUsername());
		diaryService.deleteDiary(id);
		return ResponseEntity.ok().build();
	}
}
