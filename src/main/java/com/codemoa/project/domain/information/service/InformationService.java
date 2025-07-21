//재용
package com.codemoa.project.domain.information.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codemoa.project.domain.information.entity.Lecture;
import com.codemoa.project.domain.information.mapper.InformationRecommendMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InformationService {
	
	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;
	
	@Autowired
	private InformationRecommendMapper informationRecommendMapper;
	
	public Lecture getLecture(int no, boolean isCount) {
		
		return informationRecommendMapper.getLecture(no);
	}
	
}
