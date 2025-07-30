//도영
package com.codemoa.project.domain.problem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.problem.entity.Problem;
import com.codemoa.project.domain.problem.mapper.ProblemMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProblemService {
	private final ProblemMapper problemMapper;

	public List<Problem> getProblemList() {
		return problemMapper.getProblemList();
	};

	public Problem getProblemDetail(int no) {
		return problemMapper.getProblemDetail(no);
	}

	public void addProblem(Problem problem) {
		problemMapper.addProblem(problem);
	}

	public void updateProblem(Problem problem) {
		problemMapper.updateProblem(problem);
	}
	
	public void deleteProblem(int no) {
		problemMapper.deleteProblem(no);
	}

}
