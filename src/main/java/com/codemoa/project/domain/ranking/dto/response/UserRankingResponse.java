package com.codemoa.project.domain.ranking.dto.response;

import com.codemoa.project.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserRankingResponse {

	private final int rank;
	private final String nickname;
	private final String gradeName;
	private final long weeklyPoints;
	private final int totalPoints;
	private final String gradeIconName;

	public UserRankingResponse(int rank, User user, long weeklyPoints) {
		this(rank, user.getNickname(), user.getGrade().getGradeName(), weeklyPoints, user.getTotalPoints(),
				user.getGrade().name().toLowerCase());
	}

	/**
	 * DB에 사용자가 없을 때 메인 데모용.
	 */
	public static UserRankingResponse forPreview(int rank, String nickname, String gradeName, long weeklyPoints,
			String gradeIconName) {
		return new UserRankingResponse(rank, nickname, gradeName, weeklyPoints, 0, gradeIconName);
	}

	private UserRankingResponse(int rank, String nickname, String gradeName, long weeklyPoints, int totalPoints,
			String gradeIconName) {
		this.rank = rank;
		this.nickname = nickname;
		this.gradeName = gradeName;
		this.weeklyPoints = weeklyPoints;
		this.totalPoints = totalPoints;
		this.gradeIconName = gradeIconName;
	}
}
