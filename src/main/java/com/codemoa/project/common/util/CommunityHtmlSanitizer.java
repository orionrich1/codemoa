package com.codemoa.project.common.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

/**
 * 커뮤니티 게시글·댓글 등 리치 HTML 저장 전 정제.
 * {@link Safelist#relaxed()} + Summernote에서 쓰는 일부 태그 보강.
 */
public final class CommunityHtmlSanitizer {

	private CommunityHtmlSanitizer() {
	}

	public static String sanitize(String html) {
		if (html == null || html.isBlank()) {
			return "";
		}
		Safelist allow = Safelist.relaxed()
				.addTags("figure", "figcaption", "hr")
				.addAttributes("img", "align", "alt", "title", "width", "height");
		return Jsoup.clean(html, allow);
	}
}
