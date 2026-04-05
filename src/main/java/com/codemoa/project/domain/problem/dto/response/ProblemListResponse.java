package com.codemoa.project.domain.problem.dto.response;

import com.codemoa.project.domain.problem.entity.Problem;

public record ProblemListResponse(
        int problemId,
        String userId,
        String title,
        String difficulty,
        String category,
        String regDate
) {
    public ProblemListResponse(Problem problem) {
        this(problem.getProblemId(), problem.getUserId(), problem.getTitle(),
             problem.getDifficulty(), problem.getCategory(),
             problem.getRegDate() != null ? problem.getRegDate().toLocalDate().toString() : "");
    }
}
