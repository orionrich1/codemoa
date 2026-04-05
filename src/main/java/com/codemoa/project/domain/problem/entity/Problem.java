package com.codemoa.project.domain.problem.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Problem {

    private int problemId;
    private String userId;
    private String title;
    private String content;
    private String answer;
    private String hint;
    private String difficulty;
    private String category;
    private LocalDateTime regDate;

    // MyBatis 결과 매핑용 — DB SELECT 결과를 채우기 위해 필요
    public void setProblemId(int problemId)     { this.problemId = problemId; }
    public void setUserId(String userId)        { this.userId = userId; }
    public void setTitle(String title)          { this.title = title; }
    public void setContent(String content)      { this.content = content; }
    public void setAnswer(String answer)        { this.answer = answer; }
    public void setHint(String hint)            { this.hint = hint; }
    public void setDifficulty(String difficulty){ this.difficulty = difficulty; }
    public void setCategory(String category)    { this.category = category; }
    public void setRegDate(LocalDateTime regDate){ this.regDate = regDate; }

    /** 작성자 지정 — 문제 등록 시 관리자의 userId를 부여한다. */
    public void registerAuthor(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Problem [problemId=" + problemId + ", title=" + title
                + ", content=" + content + ", answer=" + answer
                + ", hint=" + hint + ", difficulty=" + difficulty
                + ", category=" + category + "]";
    }
}
