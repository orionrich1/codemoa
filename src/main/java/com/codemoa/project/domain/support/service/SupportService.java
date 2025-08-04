package com.codemoa.project.domain.support.service;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.support.entity.Board;
import com.codemoa.project.domain.support.entity.Reply;
import com.codemoa.project.domain.support.mapper.SupportMapper;

import groovy.util.logging.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@Service
@Slf4j
public class SupportService {
	// DB 작업에 필요한 SupportMapper 객체를 의존성 주입 설정
	@Autowired
	private SupportMapper supportMapper;
	
	// FAQ 게시글 번호에 해당하는 댓글 리스트를 반환하는 메서드
	public List<Reply> replyList(int no) {
		return supportMapper.replyList(no);
	}
	
	// FAQ no에 해당하는 게시글을 읽어와 반환하는 메서드
	public Board getBoard(int no, boolean isCount) {
		if(isCount) {
			supportMapper.incrementReadCount(no);
		}
		return supportMapper.getBoard(no);
	}
	
	// FAQ 전체 게시글을 읽어와 반환하는 메서드
	public List<Board> boardList() {
		return supportMapper.boardList();
	}
	
	// Q&A no에 해당하는 게시글을 읽어와 반환하는 메서드
	public Board getBoard2(int no, boolean isCount) {
		if(isCount) {
			supportMapper.incrementReadCount2(no);
		}
		return supportMapper.getBoard2(no);
	}
		
	// Q&A 전체 게시글을 읽어와 반환하는 메서드
	public List<Board> boardList2() {
		return supportMapper.boardList2();
	}
	
	// 한 페이지에 출력할 게시글의 수를 상수로 선언
	private static final int PAGE_SIZE = 10;
	
	// 한 페이지에 출력할 페이지 그룹의 수를 상수로 선언
	private static final int PAGE_GROUP = 10;
	
	// 전체 게시글을 읽어와 반환하는 메서드
	public Map<String, Object> boardList(int pageNum) {
		
		// 요청 파라미터의 pageNum을 현재 페이지로 설정
		int currentPage = pageNum;
		
		// 현재 페이지에 해당하는 게시글 리스트의 첫 번째 행의 값을 계산
		int startRow = (currentPage - 1) * PAGE_SIZE;
		
		// SupportMapper를 이용해 전체 게시글 수를 가져온다
		int listCount = supportMapper.getBoardCount();
		
		// 현재 페이지에 해당하는 게시글 리스트를 SupportMapper를 이용해 DB에서 읽어온다
		List<Board> boardList = supportMapper.boardList(startRow, PAGE_SIZE);
		
		// 페이지 그룹 이동 처리를 위해 전체 페이지 수를 계산
		int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		// 페이지 그룹 처리를 위해 페이지 그룹별 페이지와 마지막 페이지를 계산
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
				- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		// 현재 페이지 그룹의 마지막 페이지 : 10, 20, 30...
		int endPage = startPage + PAGE_GROUP - 1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		// View 페이지에서 필요한 데이터를 Map에 저장한다
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("bList", boardList);
		modelMap.put("pageCount", pageCount);
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);
		modelMap.put("currentPage", currentPage);
		modelMap.put("listCount", listCount);
		modelMap.put("pageGroup", PAGE_GROUP);
		
		return modelMap;
	}
	
	// 전체 게시글을 읽어와 반환하는 메서드
		public Map<String, Object> boardList2(int pageNum) {
			
		// 요청 파라미터의 pageNum을 현재 페이지로 설정
		int currentPage = pageNum;
		
		// 현재 페이지에 해당하는 게시글 리스트의 첫 번째 행의 값을 계산
		int startRow = (currentPage - 1) * PAGE_SIZE;
		
		// SupportMapper를 이용해 전체 게시글 수를 가져온다
		int listCount = supportMapper.getBoardCount2();
		
		// 현재 페이지에 해당하는 게시글 리스트를 SupportMapper를 이용해 DB에서 읽어온다
		List<Board> boardList2 = supportMapper.boardList2(startRow, PAGE_SIZE);
		
		// 페이지 그룹 이동 처리를 위해 전체 페이지 수를 계산
		int pageCount = listCount / PAGE_SIZE + (listCount % PAGE_SIZE == 0 ? 0 : 1);
		
		// 페이지 그룹 처리를 위해 페이지 그룹별 페이지와 마지막 페이지를 계산
		int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
				- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
		
		// 현재 페이지 그룹의 마지막 페이지 : 10, 20, 30...
		int endPage = startPage + PAGE_GROUP - 1;
		
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		// View 페이지에서 필요한 데이터를 Map에 저장한다
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		modelMap.put("bList", boardList2);
		modelMap.put("pageCount", pageCount);
		modelMap.put("startPage", startPage);
		modelMap.put("endPage", endPage);
		modelMap.put("currentPage", currentPage);
		modelMap.put("listCount", listCount);
		modelMap.put("pageGroup", PAGE_GROUP);
		
		return modelMap;
	}
}
