package com.codemoa.project.domain.information.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContestFormRequest {

	private int contestNo;
	private String userId;
	private String title;
	private String hostOrganization;
	private String contestSource;
	private String content;
}
