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
    private Long recruitNo;		//내부 DB 고유 코드

    @Column(name = "wanted_auth_no", nullable = false)
    private String wantedAuthNo;		//고용 24 공고 고유 번호

    @Column(nullable = false)
    private String title;				// 공고 제목 (empWantedTitle)

    @Column(nullable = false)
    private String company;			//회사명

    @Column(nullable = true)
    private String region;  // 지역 (XML에 없음 → nullable)
    
    @Column(name = "sub_region", nullable = true)
    private String subRegion;  // 구/군 (XML에 없음 → nullable)
    
    @Column(name = "reg_dt", nullable = true)
    private String regDt;  // 등록일 (empWantedStdt에 매핑)
    
    @Column(name = "close_dt", nullable = true)
    private String closeDt;  // 마감일 (empWantedEndt에 매핑)
    
    @Column(nullable = false, length = 768, unique = true)
    private String url;  // 상세 페이지 URL (empWantedHomepgDetail)
    
    // 필요한 경우 타입 추가
    @Column(name = "type", nullable = true)
    private String type;  // empWantedTypeNm
    
    @Column(name = "start_date", nullable = true)
    private String startDate;  // empWantedStdt (등록일 대체용 필드, 필요하면 사용)
    
    @Column(name = "end_date", nullable = true)
    private String endDate;  // empWantedEndt (마감일 대체용 필드, 필요하면 사용)
    
    @Column(nullable = false)
    private String source;  //"일반" or "공채 속보"
}