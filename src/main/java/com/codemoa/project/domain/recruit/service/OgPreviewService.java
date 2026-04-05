package com.codemoa.project.domain.recruit.service;

import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.codemoa.project.domain.recruit.dto.response.OgPreviewResponse;

@Service
public class OgPreviewService {

	private static final OgPreviewResponse FAILURE = OgPreviewResponse.builder()
			.success(false).title("").description("").imageUrl("").build();

	public OgPreviewResponse fetchOgData(String url) {
		// C-7: SSRF 방지 — http/https 프로토콜만 허용하고 사설 IP 차단
		if (!isSafeUrl(url)) {
			return FAILURE;
		}
		try {
			Document doc = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (compatible; CodemoaBot/1.0)")
					.timeout(5000)
					.get();

			String title = extractMetaContent(doc, "og:title");
			if (title == null || title.isEmpty()) title = doc.title();

			String description = extractMetaContent(doc, "og:description");
			if (description == null || description.isEmpty()) {
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
			return FAILURE;
		}
	}

	/**
	 * C-7: http/https 이외 프로토콜과 사설 IP 대역을 차단합니다.
	 */
	private boolean isSafeUrl(String rawUrl) {
		if (rawUrl == null || rawUrl.isBlank()) return false;
		try {
			URL url = new URL(rawUrl);
			String protocol = url.getProtocol();
			if (!protocol.equals("http") && !protocol.equals("https")) return false;

			String host = url.getHost().toLowerCase();
			// 루프백 및 사설 IP 패턴 차단
			if (host.equals("localhost") || host.startsWith("127.")
					|| host.startsWith("10.")
					|| host.startsWith("192.168.")
					|| host.startsWith("169.254.")  // AWS 메타데이터
					|| host.equals("0.0.0.0")
					|| host.equals("[::1]")) {
				return false;
			}
			// 172.16.0.0/12 대역
			if (host.startsWith("172.")) {
				String[] parts = host.split("\\.");
				if (parts.length >= 2) {
					int second = Integer.parseInt(parts[1]);
					if (second >= 16 && second <= 31) return false;
				}
			}
			return true;
		} catch (MalformedURLException | NumberFormatException e) {
			return false;
		}
	}

	/**
	 * B-3: og:title 등 콜론이 포함된 property 값을 CSS 셀렉터에서 안전하게 처리하기 위해 따옴표로 감쌈
	 */
	private String extractMetaContent(Document doc, String property) {
		Element metaTag = doc.selectFirst("meta[property='" + property + "']");
		return metaTag != null ? metaTag.attr("content") : null;
	}
}
