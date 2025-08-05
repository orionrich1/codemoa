package com.codemoa.project.domain.ranking.dto.response;

import com.codemoa.project.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserRankingResponse {

    private final int rank;
    private final String nickname;
    private final String gradeName;
    private final long weeklyPoints;
    private final int totalPoints;
    private final String gradeIconName; // ▼▼▼ [필드 추가] ▼▼▼

    public UserRankingResponse(int rank, User user, long weeklyPoints) {
        this.rank = rank;
        this.nickname = user.getNickname();
        this.gradeName = user.getGrade().getGradeName();
        this.weeklyPoints = weeklyPoints;
        this.totalPoints = user.getTotalPoints();
        // ▼▼▼ [로직 추가] Enum의 영문 이름을 소문자로 변환하여 저장합니다. (예: "bronze") ▼▼▼
        this.gradeIconName = user.getGrade().name().toLowerCase();
    }
}