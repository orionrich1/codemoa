package com.codemoa.project.domain.problem.dto.request;

import com.codemoa.project.domain.problem.entity.Problem;

public class WriteProblemRequest {

    private String title;
    private String content;
    private String answer;
    private String hint;
    private String difficulty;
    private String category;

    public String getTitle()      { return title; }
    public String getContent()    { return content; }
    public String getAnswer()     { return answer; }
    public String getHint()       { return hint; }
    public String getDifficulty() { return difficulty; }
    public String getCategory()   { return category; }

    public void setTitle(String title)          { this.title = title; }
    public void setContent(String content)      { this.content = content; }
    public void setAnswer(String answer)        { this.answer = answer; }
    public void setHint(String hint)            { this.hint = hint; }
    public void setDifficulty(String difficulty){ this.difficulty = difficulty; }
    public void setCategory(String category)    { this.category = category; }

    /** 작성자를 지정하여 Problem 엔티티로 변환 */
    public Problem toEntity(String authorId) {
        Problem problem = new Problem();
        problem.setTitle(title);
        problem.setContent(content);
        problem.setAnswer(answer);
        problem.setHint(hint);
        problem.setDifficulty(difficulty);
        problem.setCategory(category);
        problem.registerAuthor(authorId);
        return problem;
    }
}
