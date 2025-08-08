package com.codemoa.project.domain.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.main.entity.MainSearch;
import com.codemoa.project.domain.main.mapper.MainMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService {
	private final MainMapper mainMapper;
	
	public List<MainSearch> searchAll(String keyword) {
		System.out.println("서비스");
		return mainMapper.searchAll(keyword);	
	}
	
}
