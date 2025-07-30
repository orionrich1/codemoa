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
	private Long recruitNo;			//채용 공고 고유번호
	
	@Column(nullable = false)
	private String title;			//공고 제목
	
	@Column(nullable = false)
	private String company;			//회사 명
	
	@Column(nullable = false)
	private String region;		//광역시/도
	
	@Column(name ="sub_region", nullable = false)
	private String subRegion;		// 구/군
	
	@Column(nullable = false, unique = true)
	private String url;		//상세 페이지 URL
}
	