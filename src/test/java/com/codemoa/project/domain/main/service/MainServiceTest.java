package com.codemoa.project.domain.main.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codemoa.project.domain.main.entity.MainSearch;
import com.codemoa.project.domain.main.mapper.MainMapper;

@ExtendWith(MockitoExtension.class)
class MainServiceTest {

	@Mock
	private MainMapper mainMapper;

	@InjectMocks
	private MainService mainService;

	@Test
	void searchAll_blankKeyword_doesNotCallMapper() {
		var map = mainService.searchAll("   ", "recent", "all");
		assertThat(map.get("emptyKeyword")).isEqualTo(Boolean.TRUE);
		assertThat(map.get("searchList")).isEqualTo(List.of());
		verify(mainMapper, never()).searchAll(anyString());
	}

	@Test
	void matchesTypeFilter_community_usesCommunityPath() {
		when(mainMapper.searchAll("k")).thenReturn(List.of(communityRow("/community/free/1"), lectureRow()));
		var map = mainService.searchAll("k", "recent", "community");
		@SuppressWarnings("unchecked")
		List<MainSearch> list = (List<MainSearch>) map.get("searchList");
		assertThat(list).hasSize(1);
		assertThat(list.get(0).getUrl()).startsWith("/community/");
	}

	private static MainSearch communityRow(String url) {
		MainSearch s = new MainSearch();
		s.setType("커뮤니티 · 자유");
		s.setWriter("u1");
		s.setTitle("t");
		s.setContent("c");
		s.setCreatedAt(LocalDateTime.now());
		s.setUrl(url);
		return s;
	}

	private static MainSearch lectureRow() {
		MainSearch s = new MainSearch();
		s.setType("강좌 추천");
		s.setWriter("u2");
		s.setTitle("lec");
		s.setContent("x");
		s.setCreatedAt(LocalDateTime.now());
		s.setUrl("/information/lectureDetail?no=1");
		return s;
	}
}
