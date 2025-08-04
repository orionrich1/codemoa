//도영
package com.codemoa.project.domain.diary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.diary.entity.Project;
import com.codemoa.project.domain.diary.mapper.DiaryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryService {
	private final DiaryMapper diaryMapper;
	
	public List<Project> getProjectList(String userId){
		return diaryMapper.getProjectList(userId);
	}
}
