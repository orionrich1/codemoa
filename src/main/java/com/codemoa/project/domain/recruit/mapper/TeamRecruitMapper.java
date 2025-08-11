package com.codemoa.project.domain.recruit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import com.codemoa.project.domain.recruit.entity.TeamRecruit;

@Mapper
public interface TeamRecruitMapper {

	// ▼▼▼ [메인 페이지용 메서드 추가] ▼▼▼
	/**
	 * 최신 팀원 모집 공고를 지정된 개수만큼 조회합니다.
	 * @param limit 가져올 개수
	 * @return TeamRecruit 리스트
	 */
	public List<TeamRecruit> findLatestRecruits(@Param("limit") int limit);
	// ▲▲▲ [메인 페이지용 메서드 추가] ▲▲▲

	int deleteRecruit(int recruitId);
	
	public void incrementViewCount(int recruitId);
	
	public void updateTeamRecruit(TeamRecruit teamRecruit);
	
	public String userIdCheck(int recruitId);

	public void insertTeamRecruit(TeamRecruit teamRecruit);
	
	public List<TeamRecruit> TeamRecruitList(Map<String, Object> params);
	
	public int getTeamRecruitCount();
	
	public int countTeamRecruit(Map<String, Object> params);
	
	public TeamRecruit getTeamRecruit(int recruitId);
	
	public Map<String, Object> teamRecruitList(int pageNum);
}