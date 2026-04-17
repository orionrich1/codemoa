//도영 회원정보 수정 요청 DTO
package com.codemoa.project.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequest {
	private String userId;
	private String isPassChange;
	private String newPass;
	@NotBlank(message = "이름을 입력해 주세요.")
	private String name;
	@NotBlank(message = "닉네임을 입력해 주세요.")
	private String nickname;
	@NotBlank(message = "휴대폰 번호를 입력해 주세요.")
	private String mobile;
	@NotBlank(message = "이메일을 입력해 주세요.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	private String email;
}
