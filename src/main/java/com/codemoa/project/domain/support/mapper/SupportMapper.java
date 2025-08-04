package com.codemoa.project.domain.support.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codemoa.project.domain.support.entity.Board;
import com.codemoa.project.domain.support.entity.Reply;

@Mapper
public interface SupportMapper {
//////////////////////////////// Q&A 게시판 ///////////////////////////////////	

	// 게시글 리스트를 DB 테이블에서 읽어와 반환하는 메소드
	public List<Board> boardList2();

	// 한 페이지에 해당하는 게시글 리스트를 DB 테이블에서 읽어와 반환하는 메서드
	public List<Board> boardList2(@Param("startRow") int startRow, @Param("num") int num);

	// DB 테이블에 등록된 전체 게시글 수를 읽어와 반환하는 메서드
	public int getBoardCount2();

	// no에 해당하는 게시글의 읽은 횟수를 DB 테이블에서 증가시키는 메서드
	public void incrementReadCount2(int no);

	// DB 테이블에서 no에 해당하는 게시글을 읽어와 Board 객체로 반환하는 메서드
	public Board getBoard2(int no);

	public List<Reply> replyList(int no);

	public void incrementReadCount(int no);

	public Board getBoard(int no);

	public List<Board> boardList();

	public int getBoardCount();

	public List<Board> boardList(int startRow, int PAGE_SIZE);

}
