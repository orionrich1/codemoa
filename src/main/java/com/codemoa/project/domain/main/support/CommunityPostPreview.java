package com.codemoa.project.domain.main.support;

import java.time.LocalDateTime;

/**
 * 메인 피드·게시판 미리보기용. 실제 {@link com.codemoa.project.domain.community.entity.CommunityBoard}와
 * 데모용 {@link StubCommunityPost}가 공통으로 구현합니다.
 */
public interface CommunityPostPreview {

	Integer getBoardNo();

	String getTitle();

	String getCategory();

	Integer getRecommend();

	LocalDateTime getCreatedAt();

	/** 데모 행이면 상세 대신 목록 등 안전한 URL로 보냅니다. */
	default boolean isStub() {
		return false;
	}
}
