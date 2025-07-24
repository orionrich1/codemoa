//종효
package com.codemoa.project.domain.recruit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import com.codemoa.project.domain.recruit.entity.TeamRecruit;

@Mapper
public interface TeamRecruitMapper {
	
	public String userIdCheck(int recruitId);

	public void insertteamRecruit(TeamRecruit teamRecruit);
	
	public List<TeamRecruit> TeamRecruitList(
			@Param("startRow") int startRow, 
			@Param("num") int num, 
			@Param("type") String type,
			@Param("keyword") String keyword
			);
	
	public int getTeamRecruitCount();
	
	public int countTeamRecruit(
			@Param("keyword") String keyword,
			@Param("type") String type
			);
	
	public TeamRecruit getTeamRecruit(int recruitId);
	
	public Map<String, Object> teamRecruitList(int pageNum);
}
