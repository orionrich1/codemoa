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
        private final int currentGradeMinPoints;
        private final int nextGradeMinPoints;
        private final String currentGradeIconName; // ▼▼▼ [필드 추가] ▼▼▼
        
        public static MyRankingInfo from(User user) {
            UserGrade currentGrade = user.getGrade();
            UserGrade nextGrade = currentGrade.getNextGrade();

            int pointsNeeded = 0;
            String nextGradeName = "최고 등급";
            int nextGradeMinPoints = user.getTotalPoints();

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
                    // ▼▼▼ [로직 추가] Enum의 영문 이름을 소문자로 변환하여 저장합니다. ▼▼▼
                    .currentGradeIconName(currentGrade.name().toLowerCase())
                    .build();
        }
    }
}