package com.codemoa.project.domain.recruit.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.codemoa.project.domain.recruit.dto.response.OgPreviewResponse;

@Service
public class OgPreviewService {

	
	 // 주어진 URL에서 OG 메타데이터를 추출하는 메서드
	public OgPreviewResponse fetchOgData(String url) {
		try {
			//1. URL 연결(User-Agent를 지정해 봇 차단 방지)
			Document doc = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (compatible; CodemoaBot/1.0 )")
					.timeout(5000)
					.get();
			
			//2. OG 메타 제목 추출, 없으면 <title> 태그 값 사용
			String title = extractMetaContent(doc, "og:title");
			if (title == null || title.isEmpty()) title = doc.title();
			
			// 3. OG 메타 태그에서 설명 추출, 없으면 <meta name="description"> 내용 사용
			String description = extractMetaContent(doc, "og:description");
			if(description == null || description.isEmpty()) {
				//<meta name = "description"... > 에서 가져오기 시도
				description = doc.select("meta[name=description]").attr("content");
			}
			
			// 4. OG 메타 태그에서 이미지 URL 추출
			String imageUrl = extractMetaContent(doc, "og:image");
			
			// 5. 추출한 데이터를 DTO에 담아 성공 응답 반환
			return OgPreviewResponse.builder()
					.success(true)
					.title(title)
					.description(description)
					.imageUrl(imageUrl)
					.build();
		} catch (Exception e) {
			// 예외 발생시 실패 응답 반환
			return OgPreviewResponse.builder()
					.success(false)
					.title("")
					.description("")
					.imageUrl("")
					.build();
		}
	}
	 // Document에서 지정한 OG 메타 태그의 content 값을 추출하는 헬퍼 메서드
	private String extractMetaContent(Document doc, String property) {
		Element metaTag = doc.selectFirst("meta[property=" + property + "]");
		return metaTag != null ? metaTag.attr("content") : null;
	}
}
