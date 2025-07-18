//도영
package com.codemoa.project.domain.problem.dto.request;

import com.codemoa.project.domain.problem.entity.Problem;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CodeSubmitRequest {
	private Problem problem;
	private String answer;
}
