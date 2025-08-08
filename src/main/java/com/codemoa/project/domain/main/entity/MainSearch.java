package com.codemoa.project.domain.main.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MainSearch {
	private String type;
	private String writer;
	private String title;
	private String content;
	private LocalDateTime createdAt;
	private String url;
}
