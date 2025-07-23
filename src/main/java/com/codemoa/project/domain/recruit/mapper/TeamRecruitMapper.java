//종효
package com.codemoa.project.domain.recruit.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;


import com.codemoa.project.domain.recruit.entity.TeamRecruit;

@Mapper
public interface TeamRecruitMapper {
	
	public String userIdCheck(int recruitId);

	public void insertTeamRecruit(TeamRecruit teamRecruit);
	
	public List<TeamRecruit> TeamRecruitList();
	public List<TeamRecruit> TeamRecruitListPaged(
			@Param("startRow") int startRow, 
			@Param("num") int num, 
			@Param("keyword") String keyword
			);
	public int getTeamRecruitCount();
	
	public int countTeamRecruit(@Param("keyword") String keyword);
	
	public TeamRecruit getTeamRecruit(int recruitId);
	
}
