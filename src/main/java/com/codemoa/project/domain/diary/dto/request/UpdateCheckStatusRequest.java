package com.codemoa.project.domain.diary.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCheckStatusRequest {
	private boolean done;
	private int no;
}
