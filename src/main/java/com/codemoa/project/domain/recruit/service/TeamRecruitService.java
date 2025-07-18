//종효
package com.codemoa.project.domain.recruit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codemoa.project.domain.recruit.entity.TeamRecruit;
import com.codemoa.project.domain.recruit.mapper.TeamRecruitMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TeamRecruitService {
	
	@Autowired
	private TeamRecruitMapper teamRecruitMapper;
	
	public void addTeamRecruit(TeamRecruit teamRecruit) {
		log.info("TeamRecruitServic: addTeamRecruit(teamRecruit)");
		teamRecruitMapper.insertTeamRecruit(teamRecruit);
	}
	
	public TeamRecruit getTeamRecruit(int recruitId) {
		log.info("TeamRecruitService: getTeamRecruit(int recruitId)");
		return teamRecruitMapper.getTeamRecruit(recruitId);
	}
	
	public List<TeamRecruit> teamRecruitList(){
		log.info("TeamRecruitService: TeamRecruitList()");
		
		return teamRecruitMapper.TeamRecruitList();
	}
}
