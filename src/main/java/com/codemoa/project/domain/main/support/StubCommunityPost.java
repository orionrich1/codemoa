package com.codemoa.project.domain.main.support;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public final class StubCommunityPost implements CommunityPostPreview {

	private final Integer boardNo;
	private final String title;
	private final String category;
	private final Integer recommend;
	private final LocalDateTime createdAt;

	@Override
	public boolean isStub() {
		return true;
	}
}
