package com.codemoa.project.domain.information.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 강좌 등록·수정 폼 수신용 (컨트롤러에서 {@code Lecture}로 변환).
 */
@Getter
@Setter
public class LectureFormRequest {

	private int recommendNo;
	private String userId;
	private String title;
	private String subtitle;
	private String category;
	private String rating;
	private String lectureSource;
	private String content1;
	private String content2;
}
