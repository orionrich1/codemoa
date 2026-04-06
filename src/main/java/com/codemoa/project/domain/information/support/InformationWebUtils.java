package com.codemoa.project.domain.information.support;

/**
 * IT 정보 화면 공통(검색 맥락 등).
 */
public final class InformationWebUtils {

	private InformationWebUtils() {
	}

	public static boolean hasSearchContext(String type, String keyword) {
		if (type == null || keyword == null) {
			return false;
		}
		String t = type.trim();
		String k = keyword.trim();
		return !t.isEmpty() && !k.isEmpty() && !"null".equalsIgnoreCase(t) && !"null".equalsIgnoreCase(k);
	}
}
