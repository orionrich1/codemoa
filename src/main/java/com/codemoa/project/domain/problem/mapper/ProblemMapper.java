//도영
package com.codemoa.project.domain.problem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codemoa.project.domain.problem.entity.Problem;

@Mapper
public interface ProblemMapper {
	List<Problem> getProblemList();
	
	List<Problem> searchProblemList(@Param("types") List<String> types,
            @Param("difficulties") List<String> difficulties);

	Problem getProblemDetail(int no);

	void addProblem(Problem problem);

	void updateProblem(Problem problem);

	void deleteProblem(int no);

	/** 오늘의 추천 문제 (날짜 기반으로 일관된 문제 선택) */
	Problem findDailyProblem(@Param("offset") int offset);

	int countProblems();
}
