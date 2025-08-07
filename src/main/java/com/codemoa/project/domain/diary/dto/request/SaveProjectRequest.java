package com.codemoa.project.domain.diary.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveProjectRequest {
	private int projectId;
	private String userId;
	private String name;
	private String description;
	private String status;
}
