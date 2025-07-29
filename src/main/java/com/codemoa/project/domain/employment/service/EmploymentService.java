package com.codemoa.project.domain.employment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codemoa.project.domain.employment.entity.Employment;
import com.codemoa.project.domain.employment.mapper.EmploymentMapper;

@Service
public class EmploymentService {
	
	@Autowired 
	private EmploymentMapper employmentMapper;
	
	public List<Employment> getAll(){
		return employmentMapper.findAll();
	}
	
	public Employment getById(Long recruitNo) {
		return employmentMapper.findById(recruitNo);
	}
	
	public void insert(Employment employment) {
		employmentMapper.insert(employment);
	}
	public void delete(Long recruitNo) {
		employmentMapper.delete(recruitNo);
	}
}
