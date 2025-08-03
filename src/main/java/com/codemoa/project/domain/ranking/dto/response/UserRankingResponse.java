package com.codemoa.project.domain.ranking.dto.response;

import com.codemoa.project.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserRankingResponse {

    private final int rank;             // 순위
    private final String nickname;      // 닉네임
    private final String gradeName;     // 등급명 (예: "골드")
    private final int totalPoints;      // 전체 누적 포인트
    private final long weeklyPoints;    // 주간 획득 포인트

    public UserRankingResponse(int rank, User user, long weeklyPoints) {
        this.rank = rank;
        this.nickname = user.getNickname();
        this.gradeName = user.getGrade().getGradeName();
        this.totalPoints = user.getTotalPoints();
        this.weeklyPoints = weeklyPoints;
    }
}