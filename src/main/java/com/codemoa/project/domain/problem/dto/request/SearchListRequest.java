package com.codemoa.project.domain.problem.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SearchListRequest {
	private int[] type;
	private int[] difficulty;
}
