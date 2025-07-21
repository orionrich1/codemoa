package com.codemoa.project.domain.recruit.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.codemoa.project.domain.recruit.dto.response.OgPreviewResponse;

@Service
public class OgPreviewService {

	public OgPreviewResponse fetchOgData(String url) {
		try {
			//1. URL 연결(User-Agent를 지정해 봇 차단 방지)
			Document doc = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (compatible; CodemoaBot/1.0 )")
					.timeout(5000)
					.get();
			
			//2. OG 메타 태그 추출
			String title = extractMetaContent(doc, "og:title");
			if (title == null || title.isEmpty()) title = doc.title();
			
			String description = extractMetaContent(doc, "og:description");
			if(description == null || description.isEmpty()) {
				//<meta name = "description"... > 에서 가져오기 시도
				description = doc.select("meta[name=description]").attr("content");
			}
			
			String imageUrl = extractMetaContent(doc, "og:image");
			
			return OgPreviewResponse.builder()
					.success(true)
					.title(title)
					.description(description)
					.imageUrl(imageUrl)
					.build();
		} catch (Exception e) {
			return OgPreviewResponse.builder()
					.success(false)
					.title("")
					.description("")
					.imageUrl("")
					.build();
		}
	}
	private String extractMetaContent(Document doc, String property) {
		Element metaTag = doc.selectFirst("meta[property=" + property + "]");
		return metaTag != null ? metaTag.attr("content") : null;
	}
}
