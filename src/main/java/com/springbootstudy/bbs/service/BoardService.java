package com.springbootstudy.bbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootstudy.bbs.domain.Board;
import com.springbootstudy.bbs.domain.Reply;
import com.springbootstudy.bbs.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {
	// DB 작업에 필요한 BoardMapper 객체를 의존성 주입 설정
	@Autowired
	private BoardMapper boardMapper;
	
	// FAQ 게시판에 추천/땡큐 정보를 업데이트하고 갱신된 추천/땡큐를 가져오는 메서드
	public Map<String, Integer> recommend(int no, String recommend) {
		boardMapper.updateRecommend(no, recommend);
		
		Board board = boardMapper.getRecommend(no);
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("recommend", board.getRecommend());
		map.put("thank", board.getThank());
		return map;
	}
	
	// FAQ 게시글 번호에 해당하는 댓글 리스트를 반환하는 메서드
	public List<Reply> replyList(int no) {
		return boardMapper.replyList(no);
	}
	
	// FAQ no에 해당하는 게시글을 읽어와 반환하는 메서드
	public Board getBoard(int no, boolean isCount) {
		if(isCount) {
			boardMapper.incrementReadCount(no);
		}
		return boardMapper.getBoard(no);
	}
	
	// FAQ 전체 게시글을 읽어와 반환하는 메서드
	public List<Board> boardList() {
		log.info("BoardService: boardList()");
		return boardMapper.boardList();
	}
	
	// Q&A no에 해당하는 게시글을 삭제하는 메서드
	public void deleteBoard(int no) {
		boardMapper.deleteBoard(no);
	}
	
	// Q&A 게시글을 수정하는 메서드
	public void updateBoard(Board board) {
		boardMapper.updateBoard(board);
	}
	
	// Q&A 게시글 수정과 삭제할때 비밀번호가 맞는지 체크하는 메서드
	public boolean isPassCheck(int no, String pass) {
		boolean result = false;
		String dbPass = boardMapper.isPassCheck(no);
		if(dbPass.equals(pass)) {
			result = true;
		}
		return result;
	}
	
	// Q&A 게시글 정보를 추가하는 메서드
	public void addBoard(Board board) {
		boardMapper.insertBoard(board);
	}
	
	// Q&A no에 해당하는 게시글을 읽어와 반환하는 메서드
	public Board getBoard2(int no, boolean isCount) {
		if(isCount) {
			boardMapper.incrementReadCount2(no);
		}
		return boardMapper.getBoard2(no);
	}
		
	// Q&A 전체 게시글을 읽어와 반환하는 메서드
	public List<Board> boardList2() {
		log.info("BoardService: boardList2()");
		return boardMapper.boardList2();
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
		
		// BoardMapper를 이용해 전체 게시글 수를 가져온다
		int listCount = boardMapper.getBoardCount();
		
		// 현재 페이지에 해당하는 게시글 리스트를 BoardMapper를 이용해 DB에서 읽어온다
		List<Board> boardList = boardMapper.boardList(startRow, PAGE_SIZE);
		
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
		
		// BoardMapper를 이용해 전체 게시글 수를 가져온다
		int listCount = boardMapper.getBoardCount2();
		
		// 현재 페이지에 해당하는 게시글 리스트를 BoardMapper를 이용해 DB에서 읽어온다
		List<Board> boardList2 = boardMapper.boardList2(startRow, PAGE_SIZE);
		
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
