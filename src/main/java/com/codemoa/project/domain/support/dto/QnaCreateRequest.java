package com.codemoa.project.domain.support.dto;

import com.codemoa.project.domain.support.entity.Qna;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaCreateRequest {
    private String title;
    private String content;

    public Qna toEntity(String writerId) {
        Qna qna = new Qna();
        qna.setTitle(this.title);
        qna.setContent(this.content);
        qna.setWriterId(writerId);
        return qna;
    }
}