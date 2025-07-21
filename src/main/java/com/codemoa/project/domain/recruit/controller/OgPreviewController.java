package com.codemoa.project.domain.recruit.controller;

import org.springframework.web.bind.annotation.*;

import com.codemoa.project.domain.recruit.dto.response.OgPreviewResponse;
import com.codemoa.project.domain.recruit.service.OgPreviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recruit")
@RequiredArgsConstructor
public class OgPreviewController {

	private final OgPreviewService ogPreviewService;
	
	@GetMapping("/og-preview")
	public OgPreviewResponse getOgPreview(@RequestParam ("url") String url) {
		return ogPreviewService.fetchOgData(url);
	}
}
