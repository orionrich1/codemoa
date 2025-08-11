package com.codemoa.project.domain.recruit.service;


import java.util.List; // 1. import 추가
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.codemoa.project.domain.recruit.entity.TeamRecruit;

public interface TeamRecruitService {
	
	// ▼▼▼ [메인 페이지용 메서드 추가] ▼▼▼
	/**
	 * 메인 페이지에 표시할 최신 팀원 모집 공고를 조회합니다.
	 * @param limit 가져올 개수
	 * @return TeamRecruit 리스트
	 */
	public List<TeamRecruit> getLatestRecruits(int limit);
	// ▲▲▲ [메인 페이지용 메서드 추가] ▲▲▲

	public boolean deleteRecruit(int recruitId);
	
	void updateTeamRecruit(TeamRecruit teamRecruit, MultipartFile uploadFile) throws Exception;
	
	void updateTeamRecruit(TeamRecruit teamRecruit);
	
    Map<String, Object> teamRecruitList(int pageNum, String type, String keyword);

    boolean userIdCheck(int recruitId, String userId);

    void addTeamRecruit(TeamRecruit teamRecruit);

    TeamRecruit getTeamRecruit(int recruitId);	
	
	void insertTeamRecruit(TeamRecruit teamRecruit);
	
	void increaseViewCount(int recruitId);
}