package com.codemoa.project.domain.problem.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProblemSubmission {

    private int submissionId;
    private String userId;
    private int problemId;
    private String submittedAnswer;
    private int aiScore;
    private String aiFeedback;
    private LocalDateTime submittedAt;
    private int pointAwarded;

    // findMyHistory JOIN 결과 (problems 테이블)
    private String problemTitle;
    private String category;
    private String difficulty;

    // MyBatis 결과 매핑용
    public void setSubmissionId(int submissionId)         { this.submissionId = submissionId; }
    public void setUserId(String userId)                  { this.userId = userId; }
    public void setProblemId(int problemId)               { this.problemId = problemId; }
    public void setSubmittedAnswer(String submittedAnswer){ this.submittedAnswer = submittedAnswer; }
    public void setAiScore(int aiScore)                   { this.aiScore = aiScore; }
    public void setAiFeedback(String aiFeedback)          { this.aiFeedback = aiFeedback; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
    public void setPointAwarded(int pointAwarded)         { this.pointAwarded = pointAwarded; }

    public void setProblemTitle(String problemTitle) { this.problemTitle = problemTitle; }
    public void setCategory(String category)         { this.category = category; }
    public void setDifficulty(String difficulty)     { this.difficulty = difficulty; }

    /** 포인트 지급 후 기록 — 동일 객체의 pointAwarded를 갱신 */
    public void recordPointAwarded(int points) {
        this.pointAwarded = points;
    }
}
