package com.codemoa.project.domain.problem.mapper;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DiffStat {
    private String difficulty;
    private int totalCount;
    private int passCount; // 70점 이상

    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public void setTotalCount(int totalCount)     { this.totalCount = totalCount; }
    public void setPassCount(int passCount)       { this.passCount = passCount; }
}
