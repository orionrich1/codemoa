package com.codemoa.project.domain.employment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codemoa.project.domain.employment.entity.Employment;
import com.codemoa.project.domain.employment.repository.EmploymentRepository;

@Service
public class EmploymentService {
	
	@Autowired 
	private EmploymentRepository employmentRepository;
	
	//전체 조회
	public List<Employment> getAll(){
		return employmentRepository.findAll();
	}
	//ID로 단건 조회
	public Employment getById(Long recruitNo) {
		return employmentRepository.findById(recruitNo).orElse(null);
	}
	//저장(신규 or 수정)
	public void insert(Employment employment) {
		employmentRepository.save(employment);
	}
	//삭제
	public void delete(Long recruitNo) {
		employmentRepository.deleteById(recruitNo);
	}
}
