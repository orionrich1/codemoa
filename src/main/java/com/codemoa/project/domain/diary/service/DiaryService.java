//도영
package com.codemoa.project.domain.diary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.diary.entity.Diary;
import com.codemoa.project.domain.diary.mapper.DiaryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryService {
	private final DiaryMapper diaryMapper;
	
	public List<Diary> getDiaryList(String userId){
		return diaryMapper.getDiaryList(userId);
	}
}
