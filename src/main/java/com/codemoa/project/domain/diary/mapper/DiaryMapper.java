//도영
package com.codemoa.project.domain.diary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codemoa.project.domain.diary.entity.Project;
import com.codemoa.project.domain.diary.entity.ProjectChecklist;
import com.codemoa.project.domain.diary.entity.ProjectDiary;

@Mapper
public interface DiaryMapper {
	public List<Project> getProjectList(String userId);

	public Project getProjectDetail(Integer projectId);

	public List<ProjectChecklist> getProjectCheckList(Integer projectId);

	public List<ProjectDiary> getProjectdiaries(Integer projectId);
}
