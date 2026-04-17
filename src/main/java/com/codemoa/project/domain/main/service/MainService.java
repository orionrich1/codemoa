package com.codemoa.project.domain.main.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.codemoa.project.domain.main.entity.MainSearch;
import com.codemoa.project.domain.main.mapper.MainMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService {
	private final MainMapper mainMapper;

	public static final int SEARCH_RESULT_MAX = 200;

	public Map<String, Object> searchAll(String keyword, String sort, String typeFilter) {
		Map<String, Object> map = new HashMap<>();
		if (!StringUtils.hasText(keyword)) {
			map.put("searchList", Collections.emptyList());
			map.put("searchCount", 0);
			map.put("sort", sort == null ? "recent" : sort);
			map.put("typeFilter", typeFilter == null ? "all" : typeFilter);
			map.put("emptyKeyword", Boolean.TRUE);
			return map;
		}

		List<MainSearch> list = new ArrayList<>(mainMapper.searchAll(keyword.trim()));

		if (StringUtils.hasText(typeFilter) && !"all".equalsIgnoreCase(typeFilter)) {
			list = list.stream().filter(s -> matchesTypeFilter(s, typeFilter)).collect(Collectors.toList());
		}

		Comparator<MainSearch> comparator = comparatorForSort(sort);
		list.sort(comparator);

		if (list.size() > SEARCH_RESULT_MAX) {
			list = new ArrayList<>(list.subList(0, SEARCH_RESULT_MAX));
			map.put("searchTruncated", Boolean.TRUE);
		}

		map.put("searchList", list);
		map.put("searchCount", list.size());
		map.put("sort", sort == null ? "recent" : sort);
		map.put("typeFilter", typeFilter == null ? "all" : typeFilter);
		return map;
	}

	private static boolean matchesTypeFilter(MainSearch s, String filter) {
		String url = s.getUrl() == null ? "" : s.getUrl();
		String type = s.getType() == null ? "" : s.getType();
		return switch (filter.toLowerCase()) {
			case "community" -> url.startsWith("/community/");
			case "lecture" -> "강좌 추천".equals(type);
			case "contest" -> "공모전 정보".equals(type);
			case "book" -> "도서 추천".equals(type);
			case "recruit" -> "팀원 모집".equals(type);
			default -> true;
		};
	}

	private static Comparator<MainSearch> comparatorForSort(String sort) {
		if (!StringUtils.hasText(sort) || "recent".equalsIgnoreCase(sort)) {
			return Comparator.comparing(MainSearch::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder()));
		}
		if ("oldest".equalsIgnoreCase(sort)) {
			return Comparator.comparing(MainSearch::getCreatedAt, Comparator.nullsLast(Comparator.naturalOrder()));
		}
		if ("title".equalsIgnoreCase(sort)) {
			return Comparator.comparing(MainSearch::getTitle, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));
		}
		return Comparator.comparing(MainSearch::getCreatedAt, Comparator.nullsLast(Comparator.reverseOrder()));
	}
}
