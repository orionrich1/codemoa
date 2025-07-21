//도영
package com.codemoa.project.domain.problem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codemoa.project.domain.problem.entity.Problem;

@Mapper
public interface ProblemMapper {
	List<Problem> getProblemList();

	Problem getProblemDetail(int no);

	void addProblem(Problem problem);

	void updateProblem(Problem problem);

	void deleteProblem(int no);
}
