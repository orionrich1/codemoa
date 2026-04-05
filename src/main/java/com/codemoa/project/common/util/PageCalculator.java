package com.codemoa.project.common.util;

import com.codemoa.project.common.dto.PageInfo;

/**
 * 2-1: 페이지네이션 계산 공통 유틸리티.
 * InformationService (lectureList, bookList, contestList) 와
 * TeamRecruitServiceImpl (teamRecruitList) 에서 복붙되던 로직을 한 곳으로 통합.
 */
public final class PageCalculator {

    private PageCalculator() {}

    /**
     * 페이지네이션 정보를 계산합니다.
     *
     * @param currentPage 현재 페이지 (1-based)
     * @param totalCount  전체 항목 수
     * @param pageSize    페이지당 항목 수
     * @param pageGroup   한 번에 노출할 페이지 번호 그룹 크기
     * @return {@link PageInfo}
     */
    public static PageInfo calculate(int currentPage, int totalCount, int pageSize, int pageGroup) {
        int totalPages = totalCount == 0 ? 1 : (int) Math.ceil((double) totalCount / pageSize);
        int startPage = (currentPage - 1) / pageGroup * pageGroup + 1;
        int endPage = Math.min(startPage + pageGroup - 1, totalPages);
        int offset = (currentPage - 1) * pageSize;
        return new PageInfo(currentPage, totalPages, startPage, endPage, offset, totalCount, pageGroup);
    }
}
