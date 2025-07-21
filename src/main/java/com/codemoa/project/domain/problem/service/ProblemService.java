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
		// 임시 아이디
		if (problem.getUserId() == null) {
			problem.setUserId("admin");
		}
		problemMapper.addProblem(problem);
	}

}
