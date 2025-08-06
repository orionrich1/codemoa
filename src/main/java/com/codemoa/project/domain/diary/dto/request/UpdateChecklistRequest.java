package com.codemoa.project.domain.diary.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateChecklistRequest {
	private int checklistId;
	private String content;
	private int priority;
}
