//종효
package com.codemoa.project.domain.employment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codemoa.project.domain.employment.entity.Employment;

@Mapper
public interface EmploymentMapper {

	List<Employment> findAll();
	
	Employment findById(Long recruitNo);
	
	void insert(Employment employment);
	
	void delete(Long recruitNo);
}
