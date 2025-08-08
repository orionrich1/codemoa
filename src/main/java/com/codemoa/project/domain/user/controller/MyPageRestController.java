//도영
package com.codemoa.project.domain.user.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Void> updateCheck(@RequestBody UpdateCheckStatusRequest request) {
		diaryService.updateCheck(request);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/addChecklist")
	public ResponseEntity<ProjectChecklist> addChecklist(@RequestBody CreateChecklistRequest request) {
		ProjectChecklist data = diaryService.addChecklist(request);
		return ResponseEntity.ok(data);
	}

	@PostMapping("/updateChecklist")
	public ResponseEntity<Void> updateChecklist(@RequestBody UpdateChecklistRequest request) {
		diaryService.updateChecklist(request);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/deleteChecklist")
	public ResponseEntity<Void> deleteChecklist(@RequestBody Map<String, Integer> map) {
		diaryService.deleteChecklist(map.get("no"));
		return ResponseEntity.ok().build();
	}

	// ======================================
	// 프로젝트 다이어리 (ProjectDiary) 관련 기능
	// ======================================
	@PostMapping("/saveDiary")
	public ResponseEntity<ProjectDiary> saveDiary(@RequestBody SaveDiaryRequest request) {
		ProjectDiary data = diaryService.saveDiary(request);
		return ResponseEntity.ok(data);
	}

	@DeleteMapping("/deleteDiary/{diaryId}")
	public ResponseEntity<Void> deleteDiary(@PathVariable("diaryId") String diaryId) {
		diaryService.deleteDiary(Integer.parseInt(diaryId));
		return ResponseEntity.ok().build();
	}
}
