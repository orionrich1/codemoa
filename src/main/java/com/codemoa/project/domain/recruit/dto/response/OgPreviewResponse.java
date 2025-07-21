package com.codemoa.project.domain.recruit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OgPreviewResponse {
	private boolean success;
	private String title;
	private String description;
	private String imageUrl;

}
