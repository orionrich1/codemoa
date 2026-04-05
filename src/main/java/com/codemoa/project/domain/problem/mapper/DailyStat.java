package com.codemoa.project.domain.problem.mapper;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DailyStat {
    private String day;    // 'yyyy-MM-dd'
    private int count;

    public void setDay(String day)   { this.day = day; }
    public void setCount(int count)  { this.count = count; }
}
