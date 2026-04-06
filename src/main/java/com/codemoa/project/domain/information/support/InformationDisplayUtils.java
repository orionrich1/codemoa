package com.codemoa.project.domain.information.support;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * Thymeleaf 등에서 공모전 D-day 계산에 사용합니다.
 */
public final class InformationDisplayUtils {

	private InformationDisplayUtils() {
	}

	public static Long daysUntilEnd(Timestamp end) {
		if (end == null) {
			return null;
		}
		LocalDate endDay = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return ChronoUnit.DAYS.between(LocalDate.now(ZoneId.systemDefault()), endDay);
	}
}
