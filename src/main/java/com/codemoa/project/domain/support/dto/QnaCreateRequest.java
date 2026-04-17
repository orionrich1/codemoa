package com.codemoa.project.domain.support.dto;

import com.codemoa.project.domain.support.entity.Qna;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaCreateRequest {

	@NotBlank(message = "제목을 입력해 주세요.")
	@Size(max = 200, message = "제목은 200자 이하여야 합니다.")
	private String title;

	@NotBlank(message = "내용을 입력해 주세요.")
	@Size(max = 5000, message = "내용은 5000자 이하여야 합니다.")
	private String content;

	public Qna toEntity(String writerId) {
		Qna qna = new Qna();
		qna.setTitle(title != null ? title.trim() : null);
		qna.setContent(content != null ? content.trim() : null);
		qna.setWriterId(writerId);
		return qna;
	}
}