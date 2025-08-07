package com.codemoa.project.domain.support.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class QnaReply {
    private Long replyId;
    private Long qnaId;
    private String content;
    private String writerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 작성자 닉네임을 담기 위한 추가 필드 (Join 결과)
    private String writerNickname;
}