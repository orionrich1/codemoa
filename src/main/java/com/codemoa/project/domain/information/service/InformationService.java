//재용
package com.codemoa.project.domain.information.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public Lecture getLecture(int no) {
		return informationRecommendMapper.getLecture(no);
	}
	
	// 한 페이지에 해당하는 게시 글 리스트 요청을 처리하는 메서드
	public Map<String, Object> lectureList(int pageNum, String type, String keyword) {
		log.info("BoardService : boardLsit(int pageNum, String type, String keyword)");
		
		// 현재 페이지
		int currentPage = pageNum;
		
		// 1페이지 = 0   2페이지 = 10  3페이지 = 20
		int startRow = (currentPage - 1) * PAGE_SIZE; 
		
		// 전체 페이지 수 계산 = 전체 게시 글의 수 / 페이지 당 게시 글 수
		// int listCount = boardDao.getBoardCount();
		int listCount = informationRecommendMapper.getLectureCount(type, keyword);
		
		// 총 페이지 갯수
		int pageCount = listCount / PAGE_SIZE 
				+ (listCount % PAGE_SIZE == 0? 0 : 1);
		
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1 
				- (currentPage % PAGE_GROUP == 0? PAGE_GROUP : 0);
		
		int endPage = startPage + PAGE_GROUP -1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		List<Lecture> lList = informationRecommendMapper.getlectureList(startRow, PAGE_SIZE, type, keyword);
		
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("lList", lList);
		modelMap.put("pageCount", pageCount);
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);
		modelMap.put("currentPage", currentPage);
		modelMap.put("pageGroup", PAGE_GROUP);
		modelMap.put("listCount", listCount);
		
		boolean searchOption = type.equals("null")||keyword.equals("null") ? false : true;
		modelMap.put("searchOption", searchOption);
		if(searchOption) {
			modelMap.put("type", type);
			modelMap.put("keyword", keyword);
		}
		
		return modelMap;
	}
	
	@Transactional
	public void updateLecture(Lecture lecture) {
		log.info(lecture.getTitle());
		log.info(String.valueOf(lecture.getRecommendNo()));
		log.info(lecture.getCategory());
		log.info(lecture.getContent1());
		informationRecommendMapper.updateLecture(lecture);
	}
	
	public void deleteLecture(int no) {
		informationRecommendMapper.deleteLecture(no);
	}
	
	public void addLecture(Lecture lecture) {
		informationRecommendMapper.addLecture(lecture);
	}
	
}
