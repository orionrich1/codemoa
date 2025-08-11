//재용
package com.codemoa.project.domain.information.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codemoa.project.domain.information.entity.Book;
import com.codemoa.project.domain.information.entity.Contest;
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
	
	// ▼▼▼ [메인 페이지를 위한 핵심 추가 메서드] ▼▼▼
	/**
	 * 메인 페이지에 표시할 최신 강의 목록을 조회합니다.
	 * @param limit 가져올 개수
	 * @return Lecture 리스트
	 */
	@Transactional(readOnly = true)
	public List<Lecture> findLatestLectures(int limit) {
		return informationRecommendMapper.findLatestLectures(limit);
	}
	// ▲▲▲ [메인 페이지를 위한 핵심 추가 메서드] ▲▲▲
	
	
	// lecture 관련
	
	public Lecture getLecture(int no) {
		return informationRecommendMapper.getLecture(no);
	}
	
	public Map<String, Object> lectureList(int pageNum, String type, String keyword, int pageSize, int pageGrop, String order) {
		log.info("pageNum : " + pageNum);
		log.info("type : " + type);
		log.info("keyword : " + keyword);
		log.info("order : " + order);
		
		int currentPage = pageNum;
		
		int startRow = (currentPage - 1) * pageSize; 
		
		int listCount = informationRecommendMapper.getLectureCount(type, keyword);
		
		int pageCount = listCount / pageSize 
				+ (listCount % pageSize == 0? 0 : 1);
		
		int startPage = (currentPage / pageGrop) * pageGrop + 1 
				- (currentPage % pageGrop == 0? pageGrop : 0);
		
		int endPage = startPage + pageGrop -1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		List<Lecture> lList = informationRecommendMapper.getlectureList(startRow, pageSize, type, keyword, order);
		
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("lList", lList);
		modelMap.put("pageCount", pageCount);
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);
		modelMap.put("currentPage", currentPage);
		modelMap.put("pageGroup", pageGrop);
		modelMap.put("listCount", listCount);
		modelMap.put("order", order);
		modelMap.put("keyword", keyword);
		
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
	
	
	// book 관련
	

	public Book getBook(int no) {
		return informationRecommendMapper.getBook(no);
	}
	
	public Map<String, Object> bookList(int pageNum, String type, String keyword, int pageSize, int pageGrop, String order) {
		log.info("pageNum : " + pageNum);
		log.info("type : " + type);
		log.info("keyword : " + keyword);
		log.info("order : " + order);
		
		int currentPage = pageNum;
		int startRow = (currentPage - 1) * pageSize; 
		int listCount = informationRecommendMapper.getBookCount(type, keyword);
		int pageCount = listCount / pageSize 
				+ (listCount % pageSize == 0? 0 : 1);
		
		int startPage = (currentPage / pageGrop) * pageGrop + 1 
				- (currentPage % pageGrop == 0? pageGrop : 0);
		
		int endPage = startPage + pageGrop -1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		
		List<Book> bList = informationRecommendMapper.getBookList(startRow, pageSize, type, keyword, order);
		
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("bList", bList);
		modelMap.put("pageCount", pageCount);
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);
		modelMap.put("currentPage", currentPage);
		modelMap.put("pageGroup", pageGrop);
		modelMap.put("listCount", listCount);
		modelMap.put("order", order);
		modelMap.put("keyword", keyword);
		
		return modelMap;
	}
	
	@Transactional
	public void updateBook(Book book) {
		informationRecommendMapper.updateBook(book);
	}
	
	public void deleteBook(int no) {
		informationRecommendMapper.deleteBook(no);
	}
	
	public void addBook(Book book) {
		informationRecommendMapper.addBook(book);
	}
	
	
	// contest 관련
	
	public Contest getContest(int no) {
		return informationRecommendMapper.getContest(no);
	}
	
	public Map<String, Object> contestList(int pageNum, String type, String keyword, int pageSize, int pageGrop, String order) {
		
		log.info("pageNum : " + pageNum);
		log.info("type : " + type);
		log.info("keyword : " + keyword);
		log.info("order : " + order);
		
		int currentPage = pageNum;
		int startRow = (currentPage - 1) * pageSize; 
		int listCount = informationRecommendMapper.getContestCount(type, keyword);
		
		int pageCount = listCount / pageSize 
				+ (listCount % pageSize == 0? 0 : 1);
		
		int startPage = (currentPage / pageGrop) * pageGrop + 1 
				- (currentPage % pageGrop == 0? pageGrop : 0);
		
		int endPage = startPage + pageGrop -1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		List<Contest> cList = informationRecommendMapper.getContestList(startRow, pageSize, type, keyword, order);
		log.info("order : " + order);
		
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("cList", cList);
		modelMap.put("pageCount", pageCount);
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);
		modelMap.put("currentPage", currentPage);
		modelMap.put("pageGroup", pageGrop);
		modelMap.put("listCount", listCount);
		modelMap.put("order", order);
		modelMap.put("keyword", keyword);
		
		return modelMap;
	}
	
	@Transactional
	public void updateContest(Contest contest) {
		log.info(contest.getTitle());
		log.info(String.valueOf(contest.getContestNo()));
		log.info(contest.getHostOrganization());
		log.info(contest.getContent());
		informationRecommendMapper.updateContest(contest);
	}
	
	public void deleteContest(int no) {
		informationRecommendMapper.deleteContest(no);
	}
	
	public void addContest(Contest contest) {
		informationRecommendMapper.addContest(contest);
	}
}