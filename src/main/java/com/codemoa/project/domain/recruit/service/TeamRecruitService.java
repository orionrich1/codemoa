//종효
package com.codemoa.project.domain.recruit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.codemoa.project.domain.recruit.entity.TeamRecruit;
import com.codemoa.project.domain.recruit.mapper.TeamRecruitMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TeamRecruitService {
	
	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP = 10;
	
	public Map<String, Object> TeamRecruitList(int pageNum){
		log.info("TeamRecruitList: teamRecruitList()int pageNum)");
		int currentPage = pageNum;
		int startRow = (currentPage -1) *PAGE_SIZE;
		int listCount = teamRecruitMapper.getTeamRecruitCount();
		List<TeamRecruit> teamRecruitList = teamRecruitMapper.teamRecruitList(startRow, PAGE_SIZE);
		int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0? 0:1);
		int startPage = (currentPage /PAGE_GROUP) * PAGE_GROUP + 1 
				- (currentPage% PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		int endPage = startPage + PAGE_GROUP -1;
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("bList", teamRecruitList);
		modelMap.put("pageCount", pageCount);
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);
		modelMap.put("currentPage", currentPage);
		modelMap.put("listCount", listCount);
		modelMap.put("pageGroup", PAGE_GROUP);
		return modelMap;
	}
	
	@Autowired
	private TeamRecruitMapper teamRecruitMapper;
	
	public boolean userIdCheck(int recruitId, String userId) {
		log.info("TeamRecruitService: userIdCheck(int recruitId, String userId)");
		boolean result =false;
		
		String dbUserId = teamRecruitMapper.userIdCheck(recruitId);
		
		if(dbUserId.equals(userId)) {
			result = true;
		}
		return result;
	}
	
	public void addTeamRecruit(TeamRecruit teamRecruit) {
		log.info("TeamRecruitService: addTeamRecruit(teamRecruit)");
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
	
	public Map<String, Object> getPagedTeamRecruitList(int page, int size, String keyword){
		
		int offset = (page -1) * size;
		List<TeamRecruit> content = teamRecruitMapper.TeamRecruitListPaged(offset, size, keyword);
		int totalCount = teamRecruitMapper.countTeamRecruit(keyword);
		int totalpages = (int) Math.ceil((double) totalCount / size);
		
		Map<String, Object> result = new HashMap<>();
		result.put("content", content);
		result.put("totalPages", totalpages);
		result.put("currentPage", page);
		result.put("keyword",  keyword);
		return result;	
	}
}
