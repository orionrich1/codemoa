//도영
package com.codemoa.project.domain.problem.entity;

import java.time.LocalDateTime;

public class Problem {

    private int problemId;          // problem_id (PK)
    private String userId;          // user_id (FK, 출제자)
    private String title;
    private String content;
    private String difficulty;      // 난이도 (예: BRONZE, SILVER, GOLD)
    private String category;        // 문제 유형 (예: DP, DFS/BFS)
    private String inputDescription;  // 입력 조건 설명
    private String outputDescription; // 출력 조건 설명
    private LocalDateTime regDate;

    // --- Getters and Setters ---
    
    public int getProblemId() { return problemId; }
    public void setProblemId(int problemId) { this.problemId = problemId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getInputDescription() { return inputDescription; }
    public void setInputDescription(String inputDescription) { this.inputDescription = inputDescription; }

    public String getOutputDescription() { return outputDescription; }
    public void setOutputDescription(String outputDescription) { this.outputDescription = outputDescription; }

    public LocalDateTime getRegDate() { return regDate; }
    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }
}