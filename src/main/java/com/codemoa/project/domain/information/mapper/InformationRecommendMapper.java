package com.codemoa.project.domain.information.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.codemoa.project.domain.information.entity.Lecture;

@Mapper
public interface InformationRecommendMapper {
	
	Lecture getLecture(int no);
}
