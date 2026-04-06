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
		return informationRecommendMapper.getContestCount(null, null, null);
	}

	@Transactional(readOnly = true)
	public List<Lecture> findRelatedLectures(int excludeNo, String category, int limit) {
		String cat = (category != null && !category.isBlank()) ? category : null;
		return informationRecommendMapper.findRelatedLectures(excludeNo, cat, limit);
	}

	@Transactional(readOnly = true)
	public List<Book> findRelatedBooks(int excludeNo, int limit) {
		return informationRecommendMapper.findRelatedBooks(excludeNo, limit);
	}

	@Transactional(readOnly = true)
	public List<Contest> findRelatedContests(int excludeNo, int limit) {
		return informationRecommendMapper.findRelatedContests(excludeNo, limit);
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
		String qType = normalizeQueryToken(type);
		String qKeyword = normalizeQueryToken(keyword);
		String qOrder = normalizeQueryToken(order);
		log.debug("lectureList pageNum={}, type={}, keyword={}, order={}", pageNum, qType, qKeyword, qOrder);

		int totalCount = informationRecommendMapper.getLectureCount(qType, qKeyword);
		PageInfo page = PageCalculator.calculate(pageNum, totalCount, pageSize, pageGroup);

		List<Lecture> lectureList = informationRecommendMapper.getlectureList(page.getOffset(), pageSize, qType, qKeyword, qOrder);

		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("lectureList", lectureList);
		modelMap.put("pageCount", page.getTotalPages());
		modelMap.put("startPage", page.getStartPage());
		modelMap.put("endPage", page.getEndPage());
		modelMap.put("currentPage", page.getCurrentPage());
		modelMap.put("pageGroup", page.getPageGroup());
		modelMap.put("listCount", page.getTotalCount());
		modelMap.put("order", emptyIfNull(qOrder));
		modelMap.put("keyword", emptyIfNull(qKeyword));
		modelMap.put("type", emptyIfNull(qType));
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
		String qType = normalizeQueryToken(type);
		String qKeyword = normalizeQueryToken(keyword);
		String qOrder = normalizeQueryToken(order);
		log.debug("bookList pageNum={}, type={}, keyword={}, order={}", pageNum, qType, qKeyword, qOrder);

		int totalCount = informationRecommendMapper.getBookCount(qType, qKeyword);
		PageInfo page = PageCalculator.calculate(pageNum, totalCount, pageSize, pageGroup);

		List<Book> bookList = informationRecommendMapper.getBookList(page.getOffset(), pageSize, qType, qKeyword, qOrder);

		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("bookList", bookList);
		modelMap.put("pageCount", page.getTotalPages());
		modelMap.put("startPage", page.getStartPage());
		modelMap.put("endPage", page.getEndPage());
		modelMap.put("currentPage", page.getCurrentPage());
		modelMap.put("pageGroup", page.getPageGroup());
		modelMap.put("listCount", page.getTotalCount());
		modelMap.put("order", emptyIfNull(qOrder));
		modelMap.put("keyword", emptyIfNull(qKeyword));
		modelMap.put("type", emptyIfNull(qType));
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
	 * 공모전 상태 필터: all | ongoing | soon | closed (soon = 마감 14일 이내)
	 */
	public Map<String, Object> contestList(int pageNum, String type, String keyword, int pageSize, int pageGroup, String order,
			String contestFilter) {
		String qType = normalizeQueryToken(type);
		String qKeyword = normalizeQueryToken(keyword);
		String qOrder = normalizeQueryToken(order);
		String statusForSql = normalizeContestStatus(contestFilter);
		String contestFilterUi = (statusForSql == null) ? "all" : statusForSql;
		log.debug("contestList pageNum={}, type={}, keyword={}, order={}, contestFilter={}", pageNum, qType, qKeyword, qOrder,
				contestFilterUi);

		int totalCount = informationRecommendMapper.getContestCount(qType, qKeyword, statusForSql);
		PageInfo page = PageCalculator.calculate(pageNum, totalCount, pageSize, pageGroup);

		List<Contest> contestList = informationRecommendMapper.getContestList(page.getOffset(), pageSize, qType, qKeyword, qOrder,
				statusForSql);

		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("contestList", contestList);
		modelMap.put("pageCount", page.getTotalPages());
		modelMap.put("startPage", page.getStartPage());
		modelMap.put("endPage", page.getEndPage());
		modelMap.put("currentPage", page.getCurrentPage());
		modelMap.put("pageGroup", page.getPageGroup());
		modelMap.put("listCount", page.getTotalCount());
		modelMap.put("order", emptyIfNull(qOrder));
		modelMap.put("keyword", emptyIfNull(qKeyword));
		modelMap.put("type", emptyIfNull(qType));
		modelMap.put("contestFilter", contestFilterUi);
		return modelMap;
	}

	private static String normalizeQueryToken(String value) {
		if (value == null) {
			return null;
		}
		String v = value.trim();
		if (v.isEmpty() || "null".equalsIgnoreCase(v)) {
			return null;
		}
		return v;
	}

	private static String emptyIfNull(String value) {
		return value == null ? "" : value;
	}

	private static String normalizeContestStatus(String contestFilter) {
		String raw = normalizeQueryToken(contestFilter);
		if (raw == null) {
			return null;
		}
		String f = raw.toLowerCase();
		if ("ongoing".equals(f) || "soon".equals(f) || "closed".equals(f)) {
			return f;
		}
		return null;
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
