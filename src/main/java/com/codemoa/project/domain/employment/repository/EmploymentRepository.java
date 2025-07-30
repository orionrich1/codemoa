package com.codemoa.project.domain.employment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codemoa.project.domain.employment.entity.Employment;

public interface EmploymentRepository extends JpaRepository<Employment, Long> {
	// 기본 CRUD + 페이징, 정렬, 쿼리 메서드 등 JPA가 알아서 제공
 
}