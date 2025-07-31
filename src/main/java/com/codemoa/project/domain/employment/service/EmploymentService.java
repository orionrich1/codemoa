package com.codemoa.project.domain.employment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.codemoa.project.domain.employment.dto.response.EmploymentDto;
import com.codemoa.project.domain.employment.entity.Employment;
import com.codemoa.project.domain.employment.repository.EmploymentRepository;

@Service
public class EmploymentService {
	
	private final EmploymentRepository employmentRepository;
	
	@Autowired
	public EmploymentService(EmploymentRepository employmentRepository) {
		this.employmentRepository = employmentRepository;
	}
	
	public List<EmploymentDto> getAllEmployment(){
		List<Employment> employmentList = employmentRepository.findAll(Sort.by(Sort.Direction.DESC, "recruitNo"));	
		return employmentList.stream()
				 	.map(EmploymentDto::fromEntity)
					.collect(Collectors.toList());
	}
	
	public Page<EmploymentDto> getEmploymentWithFilters(String region, String subRegion, String keyword, Pageable pageable){
		return employmentRepository.findByFilters(region, subRegion, keyword, pageable)
				.map(EmploymentDto::fromEntity);
	}

}
