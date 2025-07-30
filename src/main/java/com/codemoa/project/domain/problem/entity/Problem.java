//도영
package com.codemoa.project.domain.problem.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Problem {

	private int problemId; // problem_id (PK)
	private String userId; // user_id (FK, 출제자)
	private String title;
	private String content;
	private String answer;
	private String hint;
	private String difficulty; // 난이도 (예: BRONZE, SILVER, GOLD)
	private String category; // 문제 유형 (예: DP, DFS/BFS)
	private LocalDateTime regDate;

	@Override
	public String toString() {
		return "Problem [problemId=" + problemId + ", title=" + title + ", content=" + content + ", answer=" + answer
				+ ", hint=" + hint + ", difficulty=" + difficulty + ", category=" + category + "]";
	}

}