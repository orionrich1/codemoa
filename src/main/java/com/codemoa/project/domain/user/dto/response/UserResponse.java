//기찬 회원 단일 정보 응답 DTO
package com.codemoa.project.domain.user.dto.response;

import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String userId;
    private String name;
    private String nickname;
    private String mobile;
    private Integer totalPoints;
    private LocalDateTime membershipDate;

    private String gradeName;
}
