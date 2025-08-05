//도영
package com.codemoa.project.domain.diary.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codemoa.project.domain.diary.dto.request.ChecklistCreateRequest;
import com.codemoa.project.domain.diary.dto.request.UpdateChecklistRequest;
import com.codemoa.project.domain.diary.entity.ProjectChecklist;
import com.codemoa.project.domain.diary.service.DiaryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-pages/diary")
public class DiaryRestController {

	private final DiaryService diaryService;

	@PostMapping("/updateCheckBox")
	public ResponseEntity<Void> updateCheck(@RequestBody UpdateChecklistRequest request) {
		diaryService.updateChecklist(request);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/addChecklist")
	public ResponseEntity<ProjectChecklist> addChecklist(@RequestBody ChecklistCreateRequest request) {
		ProjectChecklist data = diaryService.addChecklist(request);
		return ResponseEntity.ok(data);
	}
}
