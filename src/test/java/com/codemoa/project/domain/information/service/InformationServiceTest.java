package com.codemoa.project.domain.information.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.codemoa.project.domain.information.mapper.InformationRecommendMapper;

@ExtendWith(MockitoExtension.class)
class InformationServiceTest {

	@Mock
	private InformationRecommendMapper informationRecommendMapper;

	@InjectMocks
	private InformationService informationService;

	@Test
	void contestList_normalizesNullStringParamsAndPassesStatusFilter() {
		when(informationRecommendMapper.getContestCount(isNull(), isNull(), eq("soon"))).thenReturn(0);
		when(informationRecommendMapper.getContestList(eq(0), eq(8), isNull(), isNull(), isNull(), eq("soon")))
				.thenReturn(Collections.emptyList());

		var map = informationService.contestList(1, "null", "null", 8, 10, "null", "soon");

		assertThat(map.get("contestFilter")).isEqualTo("soon");
		verify(informationRecommendMapper).getContestCount(isNull(), isNull(), eq("soon"));
		verify(informationRecommendMapper).getContestList(0, 8, null, null, null, "soon");
	}
}
