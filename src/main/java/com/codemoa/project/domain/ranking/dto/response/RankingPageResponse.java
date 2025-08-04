package com.codemoa.project.domain.ranking.dto.response;

import com.codemoa.project.domain.user.entity.User;
import com.codemoa.project.domain.user.entity.UserGrade;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RankingPageResponse {

    private final List<UserRankingResponse> weeklyTop10;
    private final MyRankingInfo myRankingInfo;

    @Getter
    @Builder
    public static class MyRankingInfo {
        private final String currentGradeName;
        private final int totalPoints;
        private final String nextGradeName;
        private final int pointsNeededForNextGrade;
        
        // ▼▼▼▼▼ [신규 추가된 필드] ▼▼▼▼▼
        private final int currentGradeMinPoints; // 현재 등급의 최소 점수
        private final int nextGradeMinPoints;    // 다음 등급의 최소 점수
        // ▲▲▲▲▲ [신규 추가된 필드] ▲▲▲▲▲

        public static MyRankingInfo from(User user) {
            UserGrade currentGrade = user.getGrade();
            UserGrade nextGrade = currentGrade.getNextGrade();

            int pointsNeeded = 0;
            String nextGradeName = "최고 등급";
            int nextGradeMinPoints = user.getTotalPoints(); // 최고 등급이면 현재 점수가 최대 점수

            if (nextGrade != null) {
                pointsNeeded = nextGrade.getMinPoints() - user.getTotalPoints();
                nextGradeName = nextGrade.getGradeName();
                nextGradeMinPoints = nextGrade.getMinPoints();
            }

            return MyRankingInfo.builder()
                    .currentGradeName(currentGrade.getGradeName())
                    .totalPoints(user.getTotalPoints())
                    .nextGradeName(nextGradeName)
                    .pointsNeededForNextGrade(Math.max(0, pointsNeeded))
                    .currentGradeMinPoints(currentGrade.getMinPoints())
                    .nextGradeMinPoints(nextGradeMinPoints)
                    .build();
        }
    }
}