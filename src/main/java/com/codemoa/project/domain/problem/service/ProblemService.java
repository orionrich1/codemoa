//도영
package com.codemoa.project.domain.problem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.problem.dto.request.SearchListRequest;
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

	public List<Problem> searchProblemList(SearchListRequest request) {
		List<String> selectedTypes = new ArrayList<>();
		int[] typeFilter = request.getType();
		for (int i = 0; i < typeFilter.length; i++) {
			if (typeFilter[i] == 1) {
				String type = switch (i) {
				case 0 -> "JAVA";
				case 1 -> "Javascript";
				case 2 -> "Python";
				default -> "";
				};
				selectedTypes.add(type);
			}
		}

		List<String> selectedDifficulties = new ArrayList<>();
		int[] difficultyFilter = request.getDifficulty();
		for (int i = 0; i < difficultyFilter.length; i++) {
			if (difficultyFilter[i] == 1) {
				String type = switch (i) {
				case 0 -> "상";
				case 1 -> "중";
				case 2 -> "하";
				default -> "";
				};
				selectedDifficulties.add(type);
			}
		}

		return problemMapper.searchProblemList(selectedTypes, selectedDifficulties);
	}

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
