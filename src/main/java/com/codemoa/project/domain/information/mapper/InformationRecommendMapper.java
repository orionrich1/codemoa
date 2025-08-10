package com.codemoa.project.domain.information.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codemoa.project.domain.information.entity.Book;
import com.codemoa.project.domain.information.entity.Contest;
import com.codemoa.project.domain.information.entity.Lecture;

@Mapper
public interface InformationRecommendMapper {
	
	// ▼▼▼ [메인 페이지용 메서드 추가] ▼▼▼
	/**
	 * 최신 강의 목록을 지정된 개수만큼 조회합니다.
	 * @param limit 가져올 개수
	 * @return Lecture 리스트
	 */
	List<Lecture> findLatestLectures(@Param("limit") int limit);
	// ▲▲▲ [메인 페이지용 메서드 추가] ▲▲▲

	// 강좌 관련
	
	List<Lecture> getlectureList(@Param("startRow") int startRow, @Param("num") int num,
			@Param("type") String type, @Param("keyword") String keyword, @Param("order") String order);
	
	Lecture getLecture(int no);
	
	int getLectureCount(@Param("type") String type, @Param("keyword") String keyword);
	
	void updateLecture(Lecture lecture);
	
	void deleteLecture(int no);
	
	void addLecture(Lecture lecture);
	
	
	// 도서 관련
	
	List<Book> getBookList(@Param("startRow") int startRow, @Param("num") int num,
			@Param("type") String type, @Param("keyword") String keyword, @Param("order") String order);

	Book getBook(int no);
	
	int getBookCount(@Param("type") String type, @Param("keyword") String keyword);
	
	void updateBook(Book book);
	
	void deleteBook(int no);
	
	void addBook(Book book);	
	
	
	
	// 공모전 관련
	
	List<Contest> getContestList(@Param("startRow") int startRow, @Param("num") int num,
			@Param("type") String type, @Param("keyword") String keyword, @Param("order") String order);

	Contest getContest(int no);
	
	int getContestCount(@Param("type") String type, @Param("keyword") String keyword);
	
	void updateContest(Contest contest);
	
	void deleteContest(int no);
	
	void addContest(Contest contest);
	
}