package com.codemoa.project.domain.user.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBanHistory {
	private LocalDateTime banDate;
	private int banDays;
	private String banReason;
	
}
