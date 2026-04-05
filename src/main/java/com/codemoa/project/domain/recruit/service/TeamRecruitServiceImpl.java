package com.codemoa.project.domain.recruit.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.codemoa.project.common.dto.PageInfo;
import com.codemoa.project.common.service.FileStorageService;
import com.codemoa.project.common.util.PageCalculator;
import com.codemoa.project.domain.recruit.entity.TeamRecruit;
import com.codemoa.project.domain.recruit.mapper.TeamRecruitMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamRecruitServiceImpl implements TeamRecruitService {

	private static final String SUB_DIR = "recruit";
	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;

	private final TeamRecruitMapper teamRecruitMapper;
	// 2-2: 공통 FileStorageService 주입
	private final FileStorageService fileStorageService;

	@Override
	@Transactional(readOnly = true)
	public List<TeamRecruit> getLatestRecruits(int limit) {
		return teamRecruitMapper.findLatestRecruits(limit);
	}

	@Override
	public boolean deleteRecruit(int recruitId) {
		return teamRecruitMapper.deleteRecruit(recruitId) > 0;
	}

	@Override
	public void increaseViewCount(int recruitId) {
		teamRecruitMapper.incrementViewCount(recruitId);
	}

	/**
	 * 2-2: 파일 처리 로직을 FileStorageService에 위임.
	 */
	@Override
	public void updateTeamRecruit(TeamRecruit teamRecruit, MultipartFile uploadFile) throws IOException {
		if (uploadFile != null && !uploadFile.isEmpty()) {
			String saveName = fileStorageService.store(uploadFile, SUB_DIR);
			if (saveName != null) teamRecruit.setAttachmentUrl(saveName);
		}
		teamRecruitMapper.updateTeamRecruit(teamRecruit);
	}

	@Override
	public void updateTeamRecruit(TeamRecruit teamRecruit) {
		teamRecruitMapper.updateTeamRecruit(teamRecruit);
	}

	/**
	 * 2-4: insertTeamRecruit 제거 — 이 메서드만 유지.
	 */
	@Override
	public void addTeamRecruit(TeamRecruit teamRecruit) {
		teamRecruitMapper.insertTeamRecruit(teamRecruit);
	}

	/**
	 * 1-4: bList → recruitList 로 키 이름 수정.
	 * 2-1: PageCalculator 사용.
	 * 3-1: 서비스 레이어의 뷰용 레이블 변환 제거 — TeamRecruit 엔티티의 getRecruitTypeName(), getStatusName()으로 위임.
	 * 1-3: TeamRecruitList (PascalCase) → findList 로 수정.
	 */
	@Override
	public Map<String, Object> teamRecruitList(int pageNum, String type, String keyword) {
		log.debug("teamRecruitList pageNum={}, type={}, keyword={}", pageNum, type, keyword);

		Map<String, Object> params = new HashMap<>();
		params.put("type", type);
		params.put("keyword", keyword);

		int totalCount = teamRecruitMapper.countTeamRecruit(params);
		PageInfo page = PageCalculator.calculate(pageNum, totalCount, PAGE_SIZE, PAGE_GROUP);

		params.put("startRow", page.getOffset());
		params.put("num", PAGE_SIZE);

		// 1-3: TeamRecruitList → findList
		List<TeamRecruit> recruitList = teamRecruitMapper.findList(params);

		// 3-1: 레이블 변환 로직 제거 — 엔티티의 getter가 처리

		Map<String, Object> modelMap = new HashMap<>();
		// 1-4: bList → recruitList
		modelMap.put("recruitList", recruitList);
		modelMap.put("pageCount", page.getTotalPages());
		modelMap.put("startPage", page.getStartPage());
		modelMap.put("endPage", page.getEndPage());
		modelMap.put("currentPage", page.getCurrentPage());
		modelMap.put("listCount", page.getTotalCount());
		modelMap.put("pageGroup", page.getPageGroup());
		return modelMap;
	}

	@Override
	public boolean userIdCheck(int recruitId, String userId) {
		String dbUserId = teamRecruitMapper.userIdCheck(recruitId);
		return dbUserId != null && dbUserId.equals(userId);
	}

	@Override
	public TeamRecruit getTeamRecruit(int recruitId) {
		return teamRecruitMapper.getTeamRecruit(recruitId);
	}
}
