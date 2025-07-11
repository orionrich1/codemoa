//도영
package com.codemoa.project.domain.problem.entity;

public class Solution {

    private int solutionId;         // solution_id (PK)
    private int problemId;          // problem_id (FK)
    private String inputExample;    // 입력 예시
    private String outputExample;   // 출력 예시
    private boolean isOfficial;     // 정답 테스트케이스 여부

    // --- Getters and Setters ---

    public int getSolutionId() { return solutionId; }
    public void setSolutionId(int solutionId) { this.solutionId = solutionId; }

    public int getProblemId() { return problemId; }
    public void setProblemId(int problemId) { this.problemId = problemId; }

    public String getInputExample() { return inputExample; }
    public void setInputExample(String inputExample) { this.inputExample = inputExample; }

    public String getOutputExample() { return outputExample; }
    public void setOutputExample(String outputExample) { this.outputExample = outputExample; }

    public boolean isOfficial() { return isOfficial; }
    public void setOfficial(boolean official) { isOfficial = official; }
}