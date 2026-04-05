package com.codemoa.project.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 2-1: 페이지네이션 계산 결과를 담는 공통 DTO.
 * InformationService, TeamRecruitServiceImpl 등 4곳에서 중복되던 계산 로직을 대체.
 */
@Getter
@AllArgsConstructor
public class PageInfo {
    private final int currentPage;
    private final int totalPages;
    private final int startPage;
    private final int endPage;
    private final int offset;
    private final int totalCount;
    private final int pageGroup;
}
