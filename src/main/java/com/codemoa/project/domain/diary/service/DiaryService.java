//도영
package com.codemoa.project.domain.diary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.diary.dto.request.CreateChecklistRequest;
import com.codemoa.project.domain.diary.dto.request.SaveProjectRequest;
import com.codemoa.project.domain.diary.dto.request.SaveDiaryRequest;
import com.codemoa.project.domain.diary.dto.request.UpdateCheckStatusRequest;
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

	// ======================================
	// 프로젝트(Project) 관련 기능
	// ======================================
	
	// 프로젝트 전체 가져오기
	public List<Project> getProjectList(String userId) {
		return diaryMapper.getProjectList(userId);
	}
	
	public List<Project> searchProjectList(String userId, String keyword) {
		return diaryMapper.searchProjectList(userId, keyword);
	}

	// 프로젝트 상세 가져오기
	public Project getProjectDetail(Integer projectId) {
		return diaryMapper.getProjectDetail(projectId);
	}
	
	public void addProject(SaveProjectRequest request) {
		diaryMapper.addProject(request); 
	}
	
	public void updateProject(SaveProjectRequest request) {
		diaryMapper.updateProject(request); 
	}
	
	public void deleteProject(int projectId) {
		diaryMapper.deleteProject(projectId);
	}

	
	// ======================================
	// 프로젝트 체크리스트 (ProjectChecklist) 관련 기능
	// ======================================
	
	// 체크리스트 리스트 가져오기
	public List<ProjectChecklist> getProjectCheckList(Integer projectId) {
		return diaryMapper.getProjectCheckList(projectId);
	}

	// 체크리스트 추가하기
	public ProjectChecklist addChecklist(CreateChecklistRequest request) {
		diaryMapper.addChecklist(request);
		return diaryMapper.getNewProjectCheckList(request.getChecklistId());
	};

	// 체크리스트의 체크 상태 업데이트하기
	public void updateCheck(UpdateCheckStatusRequest request) {
		diaryMapper.updateCheck(request);
	}
	
	// 체크리스트 내용 업데이트하기
	public void updateChecklist(UpdateChecklistRequest request) {
		diaryMapper.updateChecklist(request);
	}
	
	// 체크리스트 삭제하기
	public void deleteChecklist(int checklistId) {
		diaryMapper.deleteChecklist(checklistId);
	}

	
	// ======================================
	// 프로젝트 다이어리 (ProjectDiary) 관련 기능
	// ======================================
	
	// 다이어리 리스트 가져오기
	public List<ProjectDiary> getProjectdiaries(Integer projectId) {
		return diaryMapper.getProjectdiaries(projectId);
	}
	
	public ProjectDiary saveDiary(SaveDiaryRequest request) {
		if (request.getDiaryId() == 0) {
			diaryMapper.addDiary(request);
		}
		else {
			diaryMapper.updateDiary(request);
		}
		return diaryMapper.getDiary(request.getDiaryId());
	}
	
	public void deleteDiary(int diaryId) {
		diaryMapper.deleteDiary(diaryId);
	}
}
