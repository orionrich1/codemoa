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
	private Long recruitNo;			//채용 공고 고유번호
	
	@Column(nullable = false)
	private String title;			//공고 제목
	
	@Column(nullable = false)
	private String company;			//회사 명
	
	private String location;
	private String url;
}
	