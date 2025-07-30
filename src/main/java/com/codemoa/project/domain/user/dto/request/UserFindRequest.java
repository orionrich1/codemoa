package com.codemoa.project.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserFindRequest {
	String userId;
	String userName;
	String userPhone;
}
