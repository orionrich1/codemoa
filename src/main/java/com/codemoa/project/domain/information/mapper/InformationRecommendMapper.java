package com.codemoa.project.domain.information.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codemoa.project.domain.information.entity.Lecture;

@Mapper
public interface InformationRecommendMapper {
	
	List<Lecture> getlectureList(@Param("startRow") int startRow, @Param("num") int num,
			@Param("type") String type, @Param("keyword") String keyword);
	
	Lecture getLecture(int no);
	
	int getLectureCount(@Param("type") String type, @Param("keyword") String keyword);
	
	void updateLecture(Lecture lecture);
	
	void deleteLecture(int no);
	
	void addLecture(Lecture lecture);
	
}
