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

	/**
	 * 메인 위젯: 로그인 시 본인 프로젝트만, 비로그인 시 빈 목록.
	 * (다이어리는 마이페이지 소유 데이터 — 타인 프로젝트를 메인에 노출하지 않음)
	 */
	public List<Project> findLatestProjectsForMain(String userId, int limit) {
		if (userId == null || userId.isBlank()) {
			return List.of();
		}
		return diaryMapper.findLatestProjectsByUser(userId, limit);
	}
	
	public List<Project> searchProjectList(String userId, String keyword) {
		return diaryMapper.searchProjectList(userId, keyword);
	}

	public int countProjectsByStatus(String userId, String status) {
		return diaryMapper.countProjectsByStatus(userId, status);
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
			// B-2: addDiary 호출 후 MyBatis useGeneratedKeys로 request.diaryId에 생성된 PK가 세팅됨
			diaryMapper.addDiary(request);
			// request.getDiaryId()는 이제 실제 생성된 ID를 가짐
		} else {
			diaryMapper.updateDiary(request);
		}
		return diaryMapper.getDiary(request.getDiaryId());
	}

	public void deleteDiary(int diaryId) {
		diaryMapper.deleteDiary(diaryId);
	}

	// ======================================
	// 소유권 검증 헬퍼 (C-9)
	// ======================================

	/**
	 * 프로젝트가 해당 userId 소유인지 확인. 아니면 IllegalStateException 발생.
	 */
	public void verifyProjectOwnership(int projectId, String userId) {
		Project project = diaryMapper.getProjectDetail(projectId);
		if (project == null || !userId.equals(project.getUserId())) {
			throw new IllegalStateException("해당 프로젝트에 대한 권한이 없습니다.");
		}
	}

	/** 체크리스트 → 프로젝트 소유권 확인 */
	public void verifyChecklistOwnership(int checklistId, String userId) {
		Integer projectId = diaryMapper.getProjectIdByChecklistId(checklistId);
		if (projectId == null) {
			throw new IllegalArgumentException("존재하지 않는 체크리스트입니다.");
		}
		verifyProjectOwnership(projectId, userId);
	}

	/** 다이어리 → 프로젝트 소유권 확인 */
	public void verifyDiaryOwnership(int diaryId, String userId) {
		Integer projectId = diaryMapper.getProjectIdByDiaryId(diaryId);
		if (projectId == null) {
			throw new IllegalArgumentException("존재하지 않는 다이어리입니다.");
		}
		verifyProjectOwnership(projectId, userId);
	}
}
