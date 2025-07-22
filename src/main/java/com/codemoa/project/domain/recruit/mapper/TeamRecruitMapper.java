//종효
package com.codemoa.project.domain.recruit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codemoa.project.domain.recruit.entity.TeamRecruit;

@Mapper
public interface TeamRecruitMapper {
	
	public String userIdCheck(int revruitId);

	public void insertTeamRecruit(TeamRecruit teamRecruit);
	
	public List<TeamRecruit> TeamRecruitList();

	public TeamRecruit getTeamRecruit(int recruitId);
	

}
