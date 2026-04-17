package com.codemoa.project.domain.support.service;

import com.codemoa.project.domain.support.dto.QnaCreateRequest;
import com.codemoa.project.domain.support.entity.Qna;
import com.codemoa.project.domain.support.mapper.SupportMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("SupportService")
class SupportServiceTest {

	@Mock
	SupportMapper supportMapper;

	@InjectMocks
	SupportService supportService;

	@Test
	@DisplayName("작성자가 아니면 수정 불가")
	void updateQnaByAuthor_wrongUser_throws() {
		Qna qna = new Qna();
		qna.setQnaId(1L);
		qna.setWriterId("owner");
		qna.setAnswered(false);
		given(supportMapper.findQnaById(1L)).willReturn(Optional.of(qna));

		QnaCreateRequest req = new QnaCreateRequest();
		req.setTitle("t");
		req.setContent("c");

		assertThatThrownBy(() -> supportService.updateQnaByAuthor(1L, req, "other"))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("본인");
		verify(supportMapper, never()).updateQna(any());
	}

	@Test
	@DisplayName("답변 완료된 질문은 작성자도 삭제 불가")
	void deleteQnaByAuthor_answered_throws() {
		Qna qna = new Qna();
		qna.setQnaId(2L);
		qna.setWriterId("u1");
		qna.setAnswered(true);
		given(supportMapper.findQnaById(2L)).willReturn(Optional.of(qna));

		assertThatThrownBy(() -> supportService.deleteQnaByAuthor(2L, "u1"))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("답변");
		verify(supportMapper, never()).deleteQna(anyLong());
	}
}
