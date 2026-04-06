package com.codemoa.project.domain.employment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codemoa.project.domain.employment.dto.response.EmploymentDto;
import com.codemoa.project.domain.employment.entity.Employment;
import com.codemoa.project.domain.employment.repository.EmploymentRepository;

@Service
public class EmploymentService {
	
	private final EmploymentRepository employmentRepository;
	private final EmploymentApiService employmentApiService;

   
	public EmploymentService(EmploymentApiService employmentApiService, 
            EmploymentRepository employmentRepository) {
				this.employmentApiService = employmentApiService;
				this.employmentRepository = employmentRepository;
}
	/**
	 * 필터·페이지 크기와 일치하는 페이지네이션 맵 (총 페이지·시작/끝 페이지 번호).
	 */
	public Map<String, Integer> buildPagination(int currentPage, int totalPages, int pageGroup) {
		int tp = Math.max(0, totalPages);
		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("totalPages", tp);
		pageMap.put("currentPage", currentPage);
		if (tp == 0) {
			pageMap.put("startPage", 1);
			pageMap.put("endPage", 1);
			return pageMap;
		}
		int startPage = ((currentPage - 1) / pageGroup) * pageGroup + 1;
		int endPage = Math.min(startPage + pageGroup - 1, tp);
		pageMap.put("startPage", startPage);
		pageMap.put("endPage", endPage);
		return pageMap;
	}

	/** 최신 수집분 기준 안내용(가장 최근 PK 행의 등록일 문자열). 크롤 시각 컬럼이 없을 때 대용. */
	public Optional<String> getLatestDatasetRegDt() {
		return employmentRepository.findFirstByOrderByRecruitNoDesc().map(Employment::getRegDt);
	}
	
	
	public List<EmploymentDto> getAllEmployment(){
		List<Employment> employmentList = employmentRepository.findAll(Sort.by(Sort.Direction.DESC, "recruitNo"));	
		return employmentList.stream()
				 	.map(EmploymentDto::fromEntity)
					.collect(Collectors.toList());
	}

	public List<EmploymentDto> findLatestEmployments(int limit) {
		Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "recruitNo"));
		return employmentRepository.findAll(pageable).stream()
				.map(EmploymentDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public long countAllEmployments() {
		return employmentRepository.count();
	}
	
	public Page<EmploymentDto> getEmploymentWithFilters(String type, String keyword, Pageable pageable){
		
		 // region, subRegion, keyword가 null 또는 빈값일 때 기본값 처리 가능
	    if(type == null || type.isBlank()) {
	        type = null;
	    }
	    if(keyword == null || keyword.isBlank()) {
	        keyword = null;
	    }
	    
		return employmentRepository.findByFilters(type, keyword, pageable)
				.map(EmploymentDto::fromEntity);
	}

}
