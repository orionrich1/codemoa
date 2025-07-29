package com.codemoa.project.domain.employment.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employment {
    
	@Id
	private Long recruitNo;	
	
	private String insttNm;
	private String recruitNm;
	private String jobsCdNm;
	private String recruitSeCd;
	private String workRegion;
	private String career;
	private Timestamp receprionCloseDt;
	private String employmentUrl;
	private Boolean isScraped;
	
	@CreationTimestamp
	private Timestamp createdAt;
	
}
	