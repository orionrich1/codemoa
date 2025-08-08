package com.codemoa.project.domain.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codemoa.project.domain.main.entity.MainSearch;
import com.codemoa.project.domain.main.mapper.MainMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService {
	private final MainMapper mainMapper;
	
	public Map<String, Object>  searchAll(String keyword) {
		Map<String, Object> map = new HashMap<>();
		List<MainSearch> list = mainMapper.searchAll(keyword);
		map.put("searchList", list);
		if(!list.isEmpty())
			map.put("searchCount", list.size());
		return map;
	}
	
}
