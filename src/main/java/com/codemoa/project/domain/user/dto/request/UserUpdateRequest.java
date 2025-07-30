//도영 회원정보 수정 요청 DTO
package com.codemoa.project.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequest {
	String userId;
	String isPassChange;
	String newPass;
	String name;
	String nickname;
	String mobile;
	String email;
}
