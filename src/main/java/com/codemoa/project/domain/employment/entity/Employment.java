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
    private Long recruitNo; // 채용 공고 고유번호
    
    @Column(nullable = false, length = 255)
    private String title;   // 공고 제목
    
    @Column(nullable = false, length = 255)
    private String company; // 회사 명
    
    @Column(length = 255)
    private String location;
    
    @Column(length = 1024)
    private String url;
}
	