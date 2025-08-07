package com.codemoa.project.domain.support.service;

import com.codemoa.project.domain.support.entity.Faq;
import com.codemoa.project.domain.support.entity.Qna;
import com.codemoa.project.domain.support.entity.QnaReply;
import com.codemoa.project.domain.support.mapper.SupportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SupportService {

    private final SupportMapper supportMapper;

    // FAQ 전체 목록 조회
    @Transactional(readOnly = true)
    public List<Faq> getAllFaqs() {
        return supportMapper.findAllFaqs();
    }

    // Q&A 목록 조회 (페이지네이션 포함)
    @Transactional(readOnly = true)
    public Map<String, Object> getQnaList(int page, int pageSize, String type, String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", (page - 1) * pageSize);
        params.put("pageSize", pageSize);
        params.put("type", type);
        params.put("keyword", keyword);

        int totalCount = supportMapper.getQnaCount(params);
        List<Qna> qnaList = supportMapper.findQnaList(params);

        Map<String, Object> result = new HashMap<>();
        result.put("list", qnaList);
        result.put("totalCount", totalCount);
        // 페이지네이션을 위한 추가 정보 (Controller에서 계산 후 Model에 담을 예정)
        result.put("currentPage", page);
        result.put("pageSize", pageSize);

        return result;
    }

    // Q&A 상세 조회 (조회수 증가 포함)
    @Transactional
    public Qna getQnaWithReplies(Long qnaId) {
        supportMapper.incrementQnaViewCount(qnaId);
        Qna qna = supportMapper.findQnaById(qnaId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Q&A를 찾을 수 없습니다. id=" + qnaId));
        // 답변 목록은 필요 시 Controller에서 별도 조회 또는 여기서 조회해서 설정 가능
        return qna;
    }
    
    // Q&A 생성
    @Transactional
    public void createQna(Qna qna) {
        supportMapper.insertQna(qna);
    }
    
    // Q&A 수정
    @Transactional
    public void updateQna(Qna qna) {
        // 본인 확인 로직 등은 Controller에서 처리 후 호출
        supportMapper.updateQna(qna);
    }

    // Q&A 삭제
    @Transactional
    public void deleteQna(Long qnaId) {
        // 본인 확인 로직 등은 Controller에서 처리 후 호출
        supportMapper.deleteQna(qnaId);
    }
    
 // ▼▼▼ [FAQ 생성 서비스 추가] ▼▼▼
    @Transactional
    public void createFaq(Faq faq) {
        supportMapper.insertFaq(faq);
    }
    // ▲▲▲ [FAQ 생성 서비스 추가] ▲▲▲
    
    // 추가: Q&A 상세보기를 위한 순수 QnA 정보 조회 (조회수 증가 없음)
    @Transactional(readOnly = true)
    public Qna getQnaById(Long qnaId) {
        return supportMapper.findQnaById(qnaId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Q&A를 찾을 수 없습니다. id=" + qnaId));
    }
    
    @Transactional
    public void createReply(QnaReply reply) {
        supportMapper.insertReply(reply);
        // 답변이 달리면, 원본 Q&A 게시글의 상태를 '답변완료'로 변경
        supportMapper.updateQnaAnsweredStatus(reply.getQnaId());
    }
}