package com.codemoa.project.domain.problem.dto.response;

import java.time.LocalDateTime;

import com.codemoa.project.domain.problem.entity.ProblemSubmission;

public record MyHistoryItem(
        int submissionId,
        int problemId,
        String problemTitle,
        String category,
        String difficulty,
        int aiScore,
        LocalDateTime submittedAt,
        int pointAwarded
) {
    public MyHistoryItem(ProblemSubmission s) {
        this(s.getSubmissionId(), s.getProblemId(),
             s.getProblemTitle(), s.getCategory(), s.getDifficulty(),
             s.getAiScore(), s.getSubmittedAt(), s.getPointAwarded());
    }
}
