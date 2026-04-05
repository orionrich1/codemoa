package com.codemoa.project.domain.problem.dto.response;

import java.util.List;

import com.codemoa.project.domain.problem.mapper.DailyStat;
import com.codemoa.project.domain.problem.mapper.DiffStat;
import com.codemoa.project.domain.problem.mapper.LangStat;

public record StatsResponse(
        int totalSubmissions,
        double avgScore,
        int maxScore,
        int solvedCount,
        List<LangStat> byLang,
        List<DiffStat> byDifficulty,
        List<DailyStat> recentDaily
) {}
