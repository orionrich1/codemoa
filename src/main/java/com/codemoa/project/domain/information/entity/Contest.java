package com.codemoa.project.domain.information.entity;

import java.sql.Timestamp;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Contest {

    private int contestNo;          // contest_id (PK)
    private String userId;
    private String title;
    private String hostOrganization;            // 주최 기관
    private Timestamp regDate;
    private Timestamp startDate;
    private Timestamp endDate;
    private String contestSource;
    private String content;
    private String pass;
    private String file1;
    private int viewCount;

}