package com.codemoa.project.domain.recruit.service;


import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.codemoa.project.domain.recruit.entity.TeamRecruit;

public interface TeamRecruitService {

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
