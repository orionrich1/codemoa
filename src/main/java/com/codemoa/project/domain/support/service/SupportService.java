package com.codemoa.project.domain.support.service;

import com.codemoa.project.domain.support.FaqCategory;
import com.codemoa.project.domain.support.dto.QnaCreateRequest;
import com.codemoa.project.domain.support.entity.Faq;
import com.codemoa.project.domain.support.entity.Qna;
import com.codemoa.project.domain.support.entity.QnaReply;
import com.codemoa.project.domain.support.mapper.SupportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupportService {

    private final SupportMapper supportMapper;

    // FAQ 전체 목록 조회
    @Transactional(readOnly = true)
    public List<Faq> getAllFaqs() {
        return supportMapper.findAllFaqs();
    }

    /**
     * FAQ를 정해진 카테고리 순으로 묶는다. DB에 없는 라벨은 「일반」으로 취급한다.
     */
    @Transactional(readOnly = true)
    public boolean hasAnyFaq() {
        return supportMapper.countFaqs() > 0;
    }

    @Transactional(readOnly = true)
    public LinkedHashMap<String, List<Faq>> getFaqsGroupedByCategory() {
        List<Faq> all = new ArrayList<>(supportMapper.findAllFaqs());
        all.sort(Comparator
                .comparingInt((Faq f) -> categoryOrderIndex(normalizeFaqCategoryLabel(f.getCategory())))
                .thenComparing(f -> Optional.ofNullable(f.getFaqId()).orElse(0L), Comparator.reverseOrder()));
        LinkedHashMap<String, List<Faq>> map = new LinkedHashMap<>();
        for (String label : FaqCategory.orderedLabels()) {
            map.put(label, new ArrayList<>());
        }
        for (Faq f : all) {
            String key = normalizeFaqCategoryLabel(f.getCategory());
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(f);
        }
        return map;
    }

    private static String normalizeFaqCategoryLabel(String raw) {
        if (raw == null || raw.isBlank() || !FaqCategory.isValidLabel(raw)) {
            return FaqCategory.GENERAL.getLabel();
        }
        return raw.trim();
    }

    private static int categoryOrderIndex(String normalizedLabel) {
        List<String> order = FaqCategory.orderedLabels();
        int i = order.indexOf(normalizedLabel);
        return i >= 0 ? i : order.size();
    }

    @Transactional(readOnly = true)
    public int countUnansweredQna() {
        return supportMapper.countUnansweredQna();
    }

    @Transactional(readOnly = true)
    public int countMyUnansweredQna(String userId) {
        return supportMapper.countMyUnansweredQna(userId);
    }

    @Transactional(readOnly = true)
    public List<Qna> findLatestQnaForMain(int limit) {
        return supportMapper.findLatestQnaForMain(limit);
    }

    /**
     * @param answerFilter {@code null} 또는 {@code all} 이면 필터 없음. {@code waiting}·{@code answered} 는 XML과 동일 문자열.
     */
    @Transactional(readOnly = true)
    public Map<String, Object> getQnaList(int page, int pageSize, String type, String keyword, String answerFilter) {
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", (page - 1) * pageSize);
        params.put("pageSize", pageSize);
        params.put("type", type);
        params.put("keyword", keyword);
        if (answerFilter != null) {
            params.put("answerFilter", answerFilter);
        }

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
        supportMapper.updateQna(qna);
    }

    // Q&A 삭제
    @Transactional
    public void deleteQna(Long qnaId) {
        supportMapper.deleteQna(qnaId);
    }

    /**
     * 답변 등록 전이며 작성자 본인인 경우에만 수정 가능.
     */
    @Transactional(readOnly = true)
    public Qna getQnaForAuthorEdit(Long qnaId, String userId) {
        Qna qna = supportMapper.findQnaById(qnaId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Q&A를 찾을 수 없습니다. id=" + qnaId));
        assertAuthorCanMutateUnanswered(qna, userId);
        return qna;
    }

    @Transactional
    public void updateQnaByAuthor(Long qnaId, QnaCreateRequest request, String userId) {
        Qna qna = supportMapper.findQnaById(qnaId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Q&A를 찾을 수 없습니다. id=" + qnaId));
        assertAuthorCanMutateUnanswered(qna, userId);
        qna.setTitle(request.getTitle() != null ? request.getTitle().trim() : null);
        qna.setContent(request.getContent() != null ? request.getContent().trim() : null);
        supportMapper.updateQna(qna);
    }

    @Transactional
    public void deleteQnaByAuthor(Long qnaId, String userId) {
        Qna qna = supportMapper.findQnaById(qnaId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Q&A를 찾을 수 없습니다. id=" + qnaId));
        assertAuthorCanMutateUnanswered(qna, userId);
        supportMapper.deleteQna(qnaId);
    }

    private static void assertAuthorCanMutateUnanswered(Qna qna, String userId) {
        if (userId == null || !userId.equals(qna.getWriterId())) {
            throw new IllegalStateException("본인의 질문만 수정하거나 삭제할 수 있습니다.");
        }
        if (qna.isAnswered()) {
            throw new IllegalStateException("답변이 등록된 질문은 수정하거나 삭제할 수 없습니다.");
        }
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
        supportMapper.updateQnaAnsweredStatus(reply.getQnaId());
    }

    // Q-2: SupportController가 Mapper를 직접 호출하는 계층 위반 해소
    @Transactional(readOnly = true)
    public List<QnaReply> findRepliesByQnaId(Long qnaId) {
        return supportMapper.findRepliesByQnaId(qnaId);
    }
}