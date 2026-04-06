package com.codemoa.project.common.community;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 커뮤니티 DB 카테고리 값 → URL 경로 세그먼트 ({@code /community/{segment}/...}).
 */
public final class CommunityCategoryPaths {

    private static final Map<String, String> TO_PATH = Map.ofEntries(
            Map.entry("자유", "free"),
            Map.entry("Java", "Java"),
            Map.entry("Python", "Python"),
            Map.entry("JavaScript", "JavaScript"),
            Map.entry("C#", "C#"),
            Map.entry("Kotlin", "Kotlin")
    );

    private CommunityCategoryPaths() {
    }

    public static String toUrlSegment(String category) {
        if (!StringUtils.hasText(category)) {
            return "free";
        }
        String trimmed = category.trim();
        return TO_PATH.getOrDefault(trimmed, "etc");
    }
}
