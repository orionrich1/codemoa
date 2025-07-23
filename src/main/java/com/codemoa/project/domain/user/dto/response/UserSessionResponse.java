package com.codemoa.project.domain.user.dto.response;

import com.codemoa.project.domain.user.entity.User;

import lombok.Getter;

@Getter
public class UserSessionResponse {
    private String userId;
    private String nickname;

    // User Entity를 받아서 필요한 정보만 뽑아내는 생성자
    public UserSessionResponse(User user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
    }
}