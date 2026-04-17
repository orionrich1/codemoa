package com.codemoa.project.domain.support.dto;

import com.codemoa.project.domain.support.entity.Faq;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaqCreateRequest {

	@NotBlank(message = "카테고리를 선택해 주세요.")
	private String category;

	@NotBlank(message = "질문을 입력해 주세요.")
	@Size(max = 500, message = "질문은 500자 이하여야 합니다.")
	private String question;

	@NotBlank(message = "답변을 입력해 주세요.")
	@Size(max = 4000, message = "답변은 4000자 이하여야 합니다.")
	private String answer;

	public Faq toEntity() {
		Faq faq = new Faq();
		faq.setCategory(category != null ? category.trim() : null);
		faq.setQuestion(question != null ? question.trim() : null);
		faq.setAnswer(answer != null ? answer.trim() : null);
		return faq;
	}
}
