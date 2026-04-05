package com.codemoa.project.domain.information.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codemoa.project.common.dto.PageInfo;
import com.codemoa.project.common.util.PageCalculator;
import com.codemoa.project.domain.information.entity.Book;
import com.codemoa.project.domain.information.entity.Contest;
import com.codemoa.project.domain.information.entity.Lecture;
import com.codemoa.project.domain.information.mapper.InformationRecommendMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 3-3: @Autowired → @RequiredArgsConstructor + final 필드로 통일
@Service
@Slf4j
@RequiredArgsConstructor
public class InformationService {

	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;

	private final InformationRecommendMapper informationRecommendMapper;

	// ===================== 메인 페이지용 =====================

	@Transactional(readOnly = true)
	public List<Lecture> findLatestLectures(int limit) {
		return informationRecommendMapper.findLatestLectures(limit);
	}

	@Transactional(readOnly = true)
	public List<Book> findLatestBooks(int limit) {
		return informationRecommendMapper.findLatestBooks(limit);
	}

	@Transactional(readOnly = true)
	public List<Contest> findLatestContests(int limit) {
		return informationRecommendMapper.findLatestContests(limit);
	}

	@Transactional(readOnly = true)
	public int countContestsEndingWithinDays(int days) {
		return informationRecommendMapper.countContestsEndingWithinDays(days);
	}

	@Transactional(readOnly = true)
	public int countAllContests() {
		return informationRecommendMapper.getContestCount(null, null);
	}

	@Transactional(readOnly = true)
	public List<Contest> findContestsForMainHub(int limit, int endingSoonDays) {
		List<Contest> soon = informationRecommendMapper.findContestsEndingSoon(limit, endingSoonDays);
		if (soon.size() >= limit) return soon;

		List<Contest> latest = informationRecommendMapper.findLatestContests(limit);
		Set<Integer> seen = new LinkedHashSet<>();
		List<Contest> merged = new ArrayList<>();
		for (Contest c : soon) {
			if (seen.add(c.getContestNo())) merged.add(c);
		}
		for (Contest c : latest) {
			if (merged.size() >= limit) break;
			if (seen.add(c.getContestNo())) merged.add(c);
		}
		return merged;
	}

	// ===================== Lecture =====================

	public Lecture getLecture(int no) {
		return informationRecommendMapper.getLecture(no);
	}

	/**
	 * 1-4: pageGrop → pageGroup 오타 수정. 맵 키 lList → lectureList 로 통일.
	 * 2-1: 페이지 계산 로직을 PageCalculator로 위임.
	 */
	public Map<String, Object> lectureList(int pageNum, String type, String keyword, int pageSize, int pageGroup, String order) {
		log.debug("lectureList pageNum={}, type={}, keyword={}, order={}", pageNum, type, keyword, order);

		int totalCount = informationRecommendMapper.getLectureCount(type, keyword);
		PageInfo page = PageCalculator.calculate(pageNum, totalCount, pageSize, pageGroup);

		List<Lecture> lectureList = informationRecommendMapper.getlectureList(page.getOffset(), pageSize, type, keyword, order);

		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("lectureList", lectureList);
		modelMap.put("pageCount", page.getTotalPages());
		modelMap.put("startPage", page.getStartPage());
		modelMap.put("endPage", page.getEndPage());
		modelMap.put("currentPage", page.getCurrentPage());
		modelMap.put("pageGroup", page.getPageGroup());
		modelMap.put("listCount", page.getTotalCount());
		modelMap.put("order", order);
		modelMap.put("keyword", keyword);
		return modelMap;
	}

	@Transactional
	public void updateLecture(Lecture lecture) {
		informationRecommendMapper.updateLecture(lecture);
	}

	public void deleteLecture(int no) {
		informationRecommendMapper.deleteLecture(no);
	}

	public void addLecture(Lecture lecture) {
		informationRecommendMapper.addLecture(lecture);
	}

	// ===================== Book =====================

	public Book getBook(int no) {
		return informationRecommendMapper.getBook(no);
	}

	/**
	 * 1-4: pageGrop → pageGroup 오타 수정. 맵 키 bList → bookList 로 통일.
	 */
	public Map<String, Object> bookList(int pageNum, String type, String keyword, int pageSize, int pageGroup, String order) {
		log.debug("bookList pageNum={}, type={}, keyword={}, order={}", pageNum, type, keyword, order);

		int totalCount = informationRecommendMapper.getBookCount(type, keyword);
		PageInfo page = PageCalculator.calculate(pageNum, totalCount, pageSize, pageGroup);

		List<Book> bookList = informationRecommendMapper.getBookList(page.getOffset(), pageSize, type, keyword, order);

		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("bookList", bookList);
		modelMap.put("pageCount", page.getTotalPages());
		modelMap.put("startPage", page.getStartPage());
		modelMap.put("endPage", page.getEndPage());
		modelMap.put("currentPage", page.getCurrentPage());
		modelMap.put("pageGroup", page.getPageGroup());
		modelMap.put("listCount", page.getTotalCount());
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

	// ===================== Contest =====================

	public Contest getContest(int no) {
		return informationRecommendMapper.getContest(no);
	}

	/**
	 * 1-4: pageGrop → pageGroup 오타 수정. 맵 키 cList → contestList 로 통일.
	 */
	public Map<String, Object> contestList(int pageNum, String type, String keyword, int pageSize, int pageGroup, String order) {
		log.debug("contestList pageNum={}, type={}, keyword={}, order={}", pageNum, type, keyword, order);

		int totalCount = informationRecommendMapper.getContestCount(type, keyword);
		PageInfo page = PageCalculator.calculate(pageNum, totalCount, pageSize, pageGroup);

		List<Contest> contestList = informationRecommendMapper.getContestList(page.getOffset(), pageSize, type, keyword, order);

		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("contestList", contestList);
		modelMap.put("pageCount", page.getTotalPages());
		modelMap.put("startPage", page.getStartPage());
		modelMap.put("endPage", page.getEndPage());
		modelMap.put("currentPage", page.getCurrentPage());
		modelMap.put("pageGroup", page.getPageGroup());
		modelMap.put("listCount", page.getTotalCount());
		modelMap.put("order", order);
		modelMap.put("keyword", keyword);
		return modelMap;
	}

	@Transactional
	public void updateContest(Contest contest) {
		informationRecommendMapper.updateContest(contest);
	}

	public void deleteContest(int no) {
		informationRecommendMapper.deleteContest(no);
	}

	public void addContest(Contest contest) {
		informationRecommendMapper.addContest(contest);
	}
}
