package com.codemoa.project.domain.support.entity;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class Faq {
    private Long faqId;
    /** {@link com.codemoa.project.domain.support.FaqCategory} 라벨과 동일 문자열 */
    private String category;
    private String question;
    private String answer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}