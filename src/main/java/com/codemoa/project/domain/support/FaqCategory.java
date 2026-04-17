package com.codemoa.project.domain.support;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FAQ 저장값·폼 option value·정렬 순서의 3-way 일치를 위해 단일 enum으로 관리한다.
 */
public enum FaqCategory {
	GENERAL("일반"),
	ACCOUNT("계정"),
	CODING("문제·코딩"),
	PAYMENT("결제·환불"),
	ETC("기타");

	private final String label;

	FaqCategory(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public static List<String> orderedLabels() {
		return Arrays.stream(values()).map(FaqCategory::getLabel).collect(Collectors.toList());
	}

	public static boolean isValidLabel(String value) {
		if (value == null) {
			return false;
		}
		String t = value.trim();
		for (FaqCategory c : values()) {
			if (c.label.equals(t)) {
				return true;
			}
		}
		return false;
	}
}
