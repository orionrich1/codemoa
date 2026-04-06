package com.codemoa.project.domain.information.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Lecture {

	private int recommendNo;
	private String userId;
	private String title;
	private String subtitle;
	private String category;
	private float rating;
	private Timestamp regDate;
	private String lectureSource;
	private String content1;
	private String content2;
	private String file1;
}
