package com.codemoa.project.domain.support.dto;

import com.codemoa.project.domain.support.entity.QnaReply;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaReplyRequest {
    private Long qnaId;
    private String content;

    public QnaReply toEntity(String writerId) {
        QnaReply reply = new QnaReply();
        reply.setQnaId(this.qnaId);
        reply.setContent(this.content);
        reply.setWriterId(writerId);
        return reply;
    }
}