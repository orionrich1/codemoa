package com.codemoa.project.domain.information.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookFormRequest {

	private int bookNo;
	private String userId;
	private String title;
	private String publisher;
	private String bookSource;
	private String content;
	private String rating;
	private String isbn;
	private String totalPageNum;
}
