package com.codemoa.project.domain.community.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // JSON 처리를 위해 기본 생성자가 있는 것이 좋습니다.
public class CreateBoardRequest {

	private String title;
	private String content;
	private String category;
	private Integer stakedPoints;
	// 만약 질문글 생성 시 포인트를 바로 걸게 하려면 아래 필드도 추가할 수 있습니다.
	// private Integer stakedPoints;
}