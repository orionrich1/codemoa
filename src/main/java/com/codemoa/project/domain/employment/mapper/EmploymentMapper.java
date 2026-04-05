package com.codemoa.project.domain.employment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.codemoa.project.domain.employment.entity.Employment;

/**
 * A-2: EmploymentMapper.xml의 namespace에 대응하는 인터페이스.
 * 기존 XML이 참조하던 com.codemoa.project.domain.employment.mapper.EmploymentMapper 가
 * 존재하지 않아 고아 리소스 상태였으므로 인터페이스를 생성합니다.
 */
@Mapper
public interface EmploymentMapper {

    List<Employment> findAll();

    Employment findById(long recruitNo);

    List<Employment> findByFilters(Map<String, Object> params);

    void insert(Employment employment);

    void delete(long recruitNo);
}
