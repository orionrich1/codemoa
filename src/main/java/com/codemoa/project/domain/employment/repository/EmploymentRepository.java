package com.codemoa.project.domain.employment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;

import com.codemoa.project.domain.employment.entity.Employment;

public interface EmploymentRepository extends JpaRepository<Employment, Long> {
	// 기본 CRUD + 페이징, 정렬, 쿼리 메서드 등 JPA가 알아서 제공
 
	boolean existsByUrl(String url);
	
	
	@Query("SELECT e FROM Employment e WHERE " +
		       "(:type IS NULL OR e.type LIKE %:type%) AND " +
		       "(:keyword IS NULL OR (" +
		       "LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "LOWER(e.company) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "LOWER(e.type) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
		       ")) " +
		       "ORDER BY e.regDt DESC")
		Page<Employment> findByFilters(@Param("type") String type,
		                               @Param("keyword") String keyword,
		                               Pageable pageable);
}


