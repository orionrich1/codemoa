//도영
package com.codemoa.project.domain.diary.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateChecklistRequest {	
	private int checklistId;
	private int projectId;
	private String content;
	private int priority;
}
