package com.codemoa.project.domain.recruit.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
        // 페이징 및 검색 조건을 Map에 설정
        params.put("startRow", startRow); // 조회 시작 행 번호
        params.put("num", PAGE_SIZE); 	// 페이지당 게시글 수
        params.put("type", type);				// 검색 타입 (예: 제목, 내용 등)
        params.put("keyword", keyword);	// 검색어
        
        // 조건에 맞는 전체 게시글 개수 조회 (페이징 계산용)
        int listCount = teamRecruitMapper.countTeamRecruit(params);
        // 조건에 맞는 게시글 리스트 조회 (페이징 처리 포함)
        List<TeamRecruit> teamRecruitList = teamRecruitMapper.TeamRecruitList(params);
        
        //상태 명, 타입명 변환("TEAM_RECRUIT" -> "팀원 모집") 
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

        // 조회 결과와 페이징 계산 결과를 뷰에 전달하기 위해 한데 묶는 Map
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