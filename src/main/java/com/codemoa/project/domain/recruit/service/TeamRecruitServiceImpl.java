package com.codemoa.project.domain.recruit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.recruit.entity.TeamRecruit;
import com.codemoa.project.domain.recruit.mapper.TeamRecruitMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamRecruitServiceImpl implements TeamRecruitService {

    private static final int PAGE_SIZE = 10;
    private static final int PAGE_GROUP = 10;

    private final TeamRecruitMapper teamRecruitMapper;

    @Override
    public void updateTeamRecruit(TeamRecruit teamRecruit) {
    	teamRecruitMapper.updateTeamRecruit(teamRecruit);
    }
    
    @Override
    public void insertTeamRecruit(TeamRecruit teamRecruit) {
    	teamRecruitMapper.insertteamRecruit(teamRecruit);
    }
    
    @Override
    public Map<String, Object> teamRecruitList(int pageNum, String type, String keyword) {
        log.info("TeamRecruitList: teamRecruitList({}, {}, {})", pageNum, type, keyword);

        int currentPage = pageNum;
        int startRow = (currentPage - 1) * PAGE_SIZE;
        int listCount = teamRecruitMapper.countTeamRecruit(type, keyword);
        List<TeamRecruit> teamRecruitList = teamRecruitMapper.TeamRecruitList(startRow, PAGE_SIZE, type, keyword);
        int pageCount = (int) Math.ceil((double) listCount / PAGE_SIZE);
        int startPage = (currentPage - 1) / PAGE_GROUP * PAGE_GROUP + 1;
        int endPage = startPage + PAGE_GROUP - 1;
        if (endPage > pageCount) endPage = pageCount;

        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("bList", teamRecruitList);
        modelMap.put("pageCount", pageCount);
        modelMap.put("startPage", startPage);
        modelMap.put("endPage", endPage);
        modelMap.put("currentPage", currentPage);
        modelMap.put("listCount", listCount);
        modelMap.put("pageGroup", PAGE_GROUP);
        return modelMap;
    }

    @Override
    public boolean userIdCheck(int recruitId, String userId) {
        log.info("TeamRecruitServiceImpl: userIdCheck({}, {})", recruitId, userId);

        String dbUserId = teamRecruitMapper.userIdCheck(recruitId);
        return dbUserId != null && dbUserId.equals(userId);
    }

    @Override
    public void addTeamRecruit(TeamRecruit teamRecruit) {
        log.info("TeamRecruitServiceImpl: addTeamRecruit()");
        teamRecruitMapper.insertteamRecruit(teamRecruit);
    }

    @Override
    public TeamRecruit getTeamRecruit(int recruitId) {
        log.info("TeamRecruitServiceImpl: getTeamRecruit({})", recruitId);
        return teamRecruitMapper.getTeamRecruit(recruitId);
    }
}