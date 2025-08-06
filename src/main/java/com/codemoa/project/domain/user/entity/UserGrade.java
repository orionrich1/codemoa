package com.codemoa.project.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum UserGrade {

    // ▼▼▼ [핵심 수정 부분] ▼▼▼
    ADMIN("관리자", Integer.MAX_VALUE), // 관리자 등급 추가
    // ▲▲▲ [핵심 수정 부분] ▲▲▲

    GRANDMASTER("그랜드마스터", 100000),
    MASTER("마스터", 50000),
    DIAMOND("다이아몬드", 20000),
    PLATINUM("플래티넘", 10000),
    GOLD("골드", 5000),
    SILVER("실버", 2000),
    BRONZE("브론즈", 0);

    private final String gradeName;
    private final int minPoints;

    private static final Map<Integer, UserGrade> POINTS_TO_GRADE_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(UserGrade::getMinPoints, grade -> grade)));

    public static UserGrade getGradeForPoints(int points) {
        return Stream.of(values())
                .filter(grade -> points >= grade.getMinPoints())
                .reduce((first, second) -> second)
                .orElse(BRONZE);
    }

    public UserGrade getNextGrade() {
        if (this == ADMIN) { // 최고 등급이 ADMIN이므로 조건 변경
            return null;
        }
        // ADMIN이 가장 앞에 오도록 순서를 바꿨으므로, 로직은 그대로 유지됩니다.
        // 하지만 더 안전한 방법은 Enum의 순서에 의존하지 않는 것입니다.
        // 현재는 그대로 두어도 문제는 없습니다.
        return this.ordinal() == 0 ? null : values()[this.ordinal() - 1]; // 역순으로 다음 등급 찾기
    }
}