
package com.codemoa.project.domain.recruit.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import com.codemoa.project.domain.recruit.entity.TeamRecruit;

@Mapper
public interface TeamRecruitMapper {

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
