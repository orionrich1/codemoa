//도영
package com.codemoa.project.domain.diary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codemoa.project.domain.diary.entity.Project;

@Mapper
public interface DiaryMapper {
	public List<Project> getProjectList(String userId);
}
