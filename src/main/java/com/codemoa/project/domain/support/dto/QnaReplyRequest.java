package com.codemoa.project.domain.support.dto;

import com.codemoa.project.domain.support.entity.QnaReply;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaReplyRequest {

	@NotNull(message = "질문 정보가 없습니다.")
	private Long qnaId;

	@NotBlank(message = "답변 내용을 입력해 주세요.")
	@Size(max = 5000, message = "답변은 5000자 이하여야 합니다.")
	private String content;

	public QnaReply toEntity(String writerId) {
		QnaReply reply = new QnaReply();
		reply.setQnaId(this.qnaId);
		reply.setContent(content != null ? content.trim() : null);
		reply.setWriterId(writerId);
		return reply;
	}
}