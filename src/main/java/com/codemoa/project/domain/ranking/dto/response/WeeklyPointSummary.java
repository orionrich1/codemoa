package com.codemoa.project.domain.ranking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeeklyPointSummary {
    private String userId;
    private long weeklyPoints;
}