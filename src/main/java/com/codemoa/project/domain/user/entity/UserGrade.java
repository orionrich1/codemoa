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
    
    BRONZE("브론즈", 0),
    SILVER("실버", 2000),
    GOLD("골드", 5000),
    PLATINUM("플래티넘", 10000),
    DIAMOND("다이아몬드", 20000),
    MASTER("마스터", 50000),
    GRANDMASTER("그랜드마스터", 100000);

    private final String gradeName;
    private final int minPoints;

    // 포인트에 해당하는 등급을 찾아주는 Map (효율적인 조회를 위해)
    private static final Map<Integer, UserGrade> POINTS_TO_GRADE_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(UserGrade::getMinPoints, grade -> grade)));

    /**
     * 주어진 포인트에 맞는 등급을 반환합니다.
     * @param points 현재 사용자 포인트
     * @return 해당 포인트에 맞는 UserGrade
     */
    public static UserGrade getGradeForPoints(int points) {
        // 포인트 역순으로 등급을 순회하며 가장 적합한 등급을 찾습니다.
        return Stream.of(values())
                .filter(grade -> points >= grade.getMinPoints())
                .reduce((first, second) -> second) // 마지막으로 매칭되는(가장 높은) 등급을 선택
                .orElse(BRONZE); // 조건을 만족하는 등급이 없으면 BRONZE 반환
    }

    /**
     * 다음 등급을 반환합니다.
     * @return 다음 UserGrade, 최고 등급일 경우 null
     */
    public UserGrade getNextGrade() {
        if (this == GRANDMASTER) {
            return null; // 최고 등급
        }
        return values()[this.ordinal() + 1];
    }
}