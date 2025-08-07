package com.codemoa.project.domain.employment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
	public Map<String, Integer> getPaginationInfo(int currentPage) {
	    int PAGE_SIZE = 10;
	    int PAGE_GROUP = 10;

	    int totalPosts = (int) employmentRepository.count(); // Repository에서 총 게시글 수 가져옴
	    int pageCount = (int) Math.ceil((double) totalPosts / PAGE_SIZE);
	    int startPage = ((currentPage - 1) / PAGE_GROUP) * PAGE_GROUP + 1;
	    int endPage = Math.min(startPage + PAGE_GROUP - 1, pageCount);

	    Map<String, Integer> pageMap = new HashMap<>();
	    pageMap.put("pageCount", pageCount);
	    pageMap.put("startPage", startPage);
	    pageMap.put("endPage", endPage);
	    pageMap.put("currentPage", currentPage);

	    return pageMap;
	}
	
	
	public List<EmploymentDto> getAllEmployment(){
		List<Employment> employmentList = employmentRepository.findAll(Sort.by(Sort.Direction.DESC, "recruitNo"));	
		return employmentList.stream()
				 	.map(EmploymentDto::fromEntity)
					.collect(Collectors.toList());
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
