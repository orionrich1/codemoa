package com.codemoa.project.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPassUpdateRequest {
	String userId;
	String pass;
}
