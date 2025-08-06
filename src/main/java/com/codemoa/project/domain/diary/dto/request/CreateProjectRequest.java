package com.codemoa.project.domain.diary.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProjectRequest {
	private int projectId;
	private String userId;
	private String name;
	private String description;
	private String status;
}
