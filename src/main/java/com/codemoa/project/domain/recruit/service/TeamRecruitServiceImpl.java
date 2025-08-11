package com.codemoa.project.domain.recruit.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 1. import 추가
import org.springframework.web.multipart.MultipartFile;

import com.codemoa.project.domain.recruit.entity.TeamRecruit;
import com.codemoa.project.domain.recruit.mapper.TeamRecruitMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamRecruitServiceImpl implements TeamRecruitService {

	@Value("${file.upload.dir}")
	private String uploadDir;
	
    private static final int PAGE_SIZE = 10;
    private static final int PAGE_GROUP = 10;

    private final TeamRecruitMapper teamRecruitMapper;
    
    // ▼▼▼ [메인 페이지용 메서드 구현] ▼▼▼
    @Override
    @Transactional(readOnly = true)
    public List<TeamRecruit> getLatestRecruits(int limit) {
        return teamRecruitMapper.findLatestRecruits(limit);
    }
    // ▲▲▲ [메인 페이지용 메서드 구현] ▲▲▲

    @Override
    public boolean deleteRecruit(int recruitId) {
    	return teamRecruitMapper.deleteRecruit(recruitId) > 0;
    }
    
    @Override
    public void increaseViewCount(int recruitId) {
    	teamRecruitMapper.incrementViewCount(recruitId);
    }
     
    @Override
    public void updateTeamRecruit(TeamRecruit teamRecruit, MultipartFile uploadFile) throws Exception {
    	if (uploadFile != null && !uploadFile.isEmpty()) {
    		String fileName = uploadFile.getOriginalFilename();
    		
    		String savePath = new File(uploadDir).getAbsolutePath() + "/";
    		
    		File folder = new File(savePath);
    		if (!folder.exists()) folder.mkdirs();
    		File dest = new File(savePath + fileName);
    		uploadFile.transferTo(dest);
    		
    		teamRecruit.setAttachmentUrl(fileName);
    	}
    	teamRecruitMapper.updateTeamRecruit(teamRecruit);    
   }
    
    @Override
    public void updateTeamRecruit(TeamRecruit teamRecruit) {
    	teamRecruitMapper.updateTeamRecruit(teamRecruit);
    }
    
    
    @Override
    public void insertTeamRecruit(TeamRecruit teamRecruit) {
    	teamRecruitMapper.insertTeamRecruit(teamRecruit);
    }
    
    @Override
    public Map<String, Object> teamRecruitList(int pageNum, String type, String keyword) {
        log.info("TeamRecruitList: teamRecruitList({}, {}, {})", pageNum, type, keyword);
        log.info("teamRecruitList 호출 - type: '{}', keyword: '{}'", type, keyword);
        
        int currentPage = pageNum;
        int startRow = (currentPage - 1) * PAGE_SIZE;
        
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("num", PAGE_SIZE);
        params.put("type", type);
        params.put("keyword", keyword);
        
        int listCount = teamRecruitMapper.countTeamRecruit(params);
        List<TeamRecruit> teamRecruitList = teamRecruitMapper.TeamRecruitList(params);
        
        for (TeamRecruit tr : teamRecruitList) {
        	if("TEAM_RECRUIT".equals(tr.getRecruitType())) {
        		tr.setRecruitTypeName("팀원 모집");
        	} else if ("TEAM_JOIN".equals(tr.getRecruitType())) {
        		tr.setRecruitTypeName("참가 희망");
        	} else{
                tr.setRecruitTypeName("미정");
        }
        	if("RECRUITING".equals(tr.getStatus())) {
        		tr.setStatusName("모집중");
        		} else if ("COMPLETED".equals(tr.getStatus())) {
        			tr.setStatusName("모집 완료");
        		} else {
        			tr.setStatusName("미정");
        		}
        	}
        
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
        teamRecruitMapper.insertTeamRecruit(teamRecruit);
    }

    @Override
    public TeamRecruit getTeamRecruit(int recruitId) {
        log.info("TeamRecruitServiceImpl: getTeamRecruit({})", recruitId);
        return teamRecruitMapper.getTeamRecruit(recruitId);
    }
}