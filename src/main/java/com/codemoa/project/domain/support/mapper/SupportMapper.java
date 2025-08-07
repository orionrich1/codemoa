package com.codemoa.project.domain.support.mapper;

import com.codemoa.project.domain.support.entity.Faq;
import com.codemoa.project.domain.support.entity.Qna;
import com.codemoa.project.domain.support.entity.QnaReply;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface SupportMapper {
    // FAQ
    List<Faq> findAllFaqs();
    
    void insertFaq(Faq faq); // 메서드 추가
    
    // Q&A
    int getQnaCount(Map<String, Object> params);
    List<Qna> findQnaList(Map<String, Object> params);
    Optional<Qna> findQnaById(Long qnaId);
    void insertQna(Qna qna);
    void updateQna(Qna qna);
    void deleteQna(Long qnaId);
    void incrementQnaViewCount(Long qnaId);
    void updateQnaAnsweredStatus(Long qnaId); // 메서드 추가

    // Q&A Reply
    List<QnaReply> findRepliesByQnaId(Long qnaId);
    void insertReply(QnaReply reply);
    void deleteReply(Long replyId);
    Optional<QnaReply> findReplyById(Long replyId);
}