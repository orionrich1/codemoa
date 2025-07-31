package com.codemoa.project.domain.employment.entity;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employment")
public class Employment {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recruit_no")
	private Long recruitNo;			//내부 DB에서 사용하는 고유번호 (PK)
	
	@Column(nullable = false)
	private String wantedAuthNo;  //고용24 채용 공고 번호(중복 방지용)
	
	@Column(nullable = false)
	private String title;			//공고 제목
	
	@Column(nullable = false)
	private String company;			//회사 명
	
	@Column(nullable = false)
	private String region;		//광역시/도
	
	@Column(name ="sub_region", nullable = false)
	private String subRegion;		// 구/군
	
	@Column(nullable = false)
	private String regDt;		//등록일
	
	@Column(name = "close_dt", nullable = false)
	private String closeDt;			//마감일
	
	
	@Column(nullable = false)
	private String url;		//상세 페이지 URL
}
	