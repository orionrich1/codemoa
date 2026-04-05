package com.codemoa.project.domain.problem.mapper;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LangStat {
    private String category;
    private double avgScore;

    public void setCategory(String category) { this.category = category; }
    public void setAvgScore(double avgScore)  { this.avgScore = avgScore; }
}
