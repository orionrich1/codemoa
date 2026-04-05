package com.codemoa.project.domain.problem.dto.request;

public class SaveSubmissionRequest {

    private int problemId;
    private String submittedAnswer;
    private int aiScore;
    private String aiFeedback;

    public int getProblemId()         { return problemId; }
    public String getSubmittedAnswer(){ return submittedAnswer; }
    public int getAiScore()           { return aiScore; }
    public String getAiFeedback()     { return aiFeedback; }

    public void setProblemId(int problemId)                  { this.problemId = problemId; }
    public void setSubmittedAnswer(String submittedAnswer)   { this.submittedAnswer = submittedAnswer; }
    public void setAiScore(int aiScore)                      { this.aiScore = aiScore; }
    public void setAiFeedback(String aiFeedback)             { this.aiFeedback = aiFeedback; }
}
