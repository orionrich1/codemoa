package com.codemoa.project.domain.information.entity;

import java.sql.Timestamp;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {
	
	private int bookNo;
	private String userId;
	private String title;
	private String publisher;
	private Timestamp regDate; 
	private Timestamp pubDate;
	private String bookSource;
	private String content;
	private String file1;
	private float rating;
	private String isbn;
	private int totalPageNum;
	private int viewCount;
}
