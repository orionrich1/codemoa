package com.codemoa.project.domain.support.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	private int no;
	private int bbsNo;
	private String replyContent;
	private String replyWriter;
	private Timestamp regDate;
}