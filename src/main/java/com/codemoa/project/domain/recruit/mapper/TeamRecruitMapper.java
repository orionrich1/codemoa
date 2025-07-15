//종효
package com.codemoa.project.domain.recruit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codemoa.project.domain.recruit.entity.TeamRecruit;

@Mapper
public interface TeamRecruitMapper {
	public List<TeamRecruit> teamRecruitList();
	

}
