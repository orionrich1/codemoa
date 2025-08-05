//도영
package com.codemoa.project.domain.diary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.diary.dto.request.ChecklistCreateRequest;
import com.codemoa.project.domain.diary.dto.request.UpdateChecklistRequest;
import com.codemoa.project.domain.diary.entity.Project;
import com.codemoa.project.domain.diary.entity.ProjectChecklist;
import com.codemoa.project.domain.diary.entity.ProjectDiary;
import com.codemoa.project.domain.diary.mapper.DiaryMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryService {

	private final DiaryMapper diaryMapper;

	public List<Project> getProjectList(String userId) {
		return diaryMapper.getProjectList(userId);
	}

	public Project getProjectDetail(Integer projectId) {
		return diaryMapper.getProjectDetail(projectId);
	}

	public List<ProjectChecklist> getProjectCheckList(Integer projectId) {
		return diaryMapper.getProjectCheckList(projectId);
	}

	public List<ProjectDiary> getProjectdiaries(Integer projectId) {
		return diaryMapper.getProjectdiaries(projectId);
	}
	
	public ProjectChecklist addChecklist(ChecklistCreateRequest request) {
		diaryMapper.addChecklist(request);
		return diaryMapper.getNewProjectCheckList(request.getChecklistId());
	};
	
	public void updateChecklist(UpdateChecklistRequest request) {
		diaryMapper.updateChecklist(request);
	}
}
