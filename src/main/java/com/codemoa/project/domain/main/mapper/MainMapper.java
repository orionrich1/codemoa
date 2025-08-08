package com.codemoa.project.domain.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codemoa.project.domain.main.entity.MainSearch;

@Mapper
public interface MainMapper {
	List<MainSearch> searchAll(String keyword);
}
