package com.codemoa.project.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPassUpdateRequest {

    @NotBlank(message = "아이디가 필요합니다.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String pass;

    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    private String passConfirm;
}
