package com.codemoa.project.domain.problem.dto.request;

import com.codemoa.project.domain.problem.entity.Problem;

public class UpdateProblemRequest {

    private int problemId;
    private String title;
    private String content;
    private String answer;
    private String hint;
    private String difficulty;
    private String category;

    public int getProblemId()     { return problemId; }
    public String getTitle()      { return title; }
    public String getContent()    { return content; }
    public String getAnswer()     { return answer; }
    public String getHint()       { return hint; }
    public String getDifficulty() { return difficulty; }
    public String getCategory()   { return category; }

    public void setProblemId(int problemId)         { this.problemId = problemId; }
    public void setTitle(String title)              { this.title = title; }
    public void setContent(String content)          { this.content = content; }
    public void setAnswer(String answer)            { this.answer = answer; }
    public void setHint(String hint)                { this.hint = hint; }
    public void setDifficulty(String difficulty)    { this.difficulty = difficulty; }
    public void setCategory(String category)        { this.category = category; }

    /** Problem 엔티티로 변환 (수정이므로 작성자 변경 없음) */
    public Problem toEntity() {
        Problem problem = new Problem();
        problem.setProblemId(problemId);
        problem.setTitle(title);
        problem.setContent(content);
        problem.setAnswer(answer);
        problem.setHint(hint);
        problem.setDifficulty(difficulty);
        problem.setCategory(category);
        return problem;
    }
}
