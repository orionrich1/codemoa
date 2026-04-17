//도영
package com.codemoa.project.domain.diary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codemoa.project.domain.diary.dto.request.CreateChecklistRequest;
import com.codemoa.project.domain.diary.dto.request.SaveProjectRequest;
import com.codemoa.project.domain.diary.dto.request.SaveDiaryRequest;
import com.codemoa.project.domain.diary.dto.request.UpdateCheckStatusRequest;
import com.codemoa.project.domain.diary.dto.request.UpdateChecklistRequest;
import com.codemoa.project.domain.diary.entity.Project;
import com.codemoa.project.domain.diary.entity.ProjectChecklist;
import com.codemoa.project.domain.diary.entity.ProjectDiary;

@Mapper
public interface DiaryMapper {

	// ======================================
	// 프로젝트(Project) 관련 기능
	// ======================================
	public List<Project> getProjectList(String userId);

	public List<Project> searchProjectList(@Param("userId") String userId, @Param("keyword") String keyword);

	/** 메인 페이지용: 해당 사용자의 최신 프로젝트만 */
	List<Project> findLatestProjectsByUser(@Param("userId") String userId, @Param("limit") int limit);

	int countProjectsByStatus(@Param("userId") String userId, @Param("status") String status);

	public Project getProjectDetail(Integer projectId);

	public void addProject(SaveProjectRequest request);

	public void updateProject(SaveProjectRequest request);

	public void deleteProject(int projectId);

	// ======================================
	// 프로젝트 체크리스트 (ProjectChecklist) 관련 기능
	// ======================================
	public List<ProjectChecklist> getProjectCheckList(Integer projectId);

	public void updateCheck(UpdateCheckStatusRequest request);

	public void addChecklist(CreateChecklistRequest request);

	public ProjectChecklist getNewProjectCheckList(int checklistId);

	public void updateChecklist(UpdateChecklistRequest request);

	public void deleteChecklist(int checklistId);

	/** 체크리스트가 속한 프로젝트 ID 조회 (소유권 검증용) */
	public Integer getProjectIdByChecklistId(int checklistId);

	// ======================================
	// 프로젝트 다이어리 (ProjectDiary) 관련 기능
	// ======================================
	public List<ProjectDiary> getProjectdiaries(Integer projectId);

	public int addDiary(SaveDiaryRequest request);

	public void updateDiary(SaveDiaryRequest request);

	public ProjectDiary getDiary(int diaryId);

	public void deleteDiary(int diaryId);

	/** 다이어리가 속한 프로젝트 ID 조회 (소유권 검증용) */
	public Integer getProjectIdByDiaryId(int diaryId);
}
