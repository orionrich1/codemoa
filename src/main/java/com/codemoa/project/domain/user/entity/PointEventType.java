package com.codemoa.project.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 포인트 획득/사용 이벤트의 종류를 정의하는 Enum
 */
@Getter
@RequiredArgsConstructor
public enum PointEventType {

    SIGN_UP("회원가입", 1000),
    DAILY_LOGIN("일일 로그인", 100),
    CREATE_POST("게시글 작성", 15),
    CREATE_COMMENT("댓글 작성", 5);

    private final String description; // 설명
    private final int points;         // 지급/차감 포인트
}