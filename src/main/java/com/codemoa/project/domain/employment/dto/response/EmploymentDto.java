package com.codemoa.project.domain.employment.dto.response;

import com.codemoa.project.domain.employment.entity.Employment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmploymentDto {

    private Long recruitNo;
    private String title;       // 공고 제목
    private String company;     // 회사 명
    private String region;      // 지역 (시/도)
    private String subRegion;   // 세부 지역 (구/군)
    private String type;        // 공고 유형
    private String startDate;   // 모집 시작일
    private String deadline;    // 모집 마감일
    private String url;         // 상세 페이지 URL
    private String regDt;

    public static EmploymentDto fromEntity(Employment e) {
        return new EmploymentDto(
                e.getRecruitNo(),
                e.getTitle(),
                e.getCompany(),
                e.getRegion(),
                e.getSubRegion(),
                e.getType(),
                e.getStartDate(),
                e.getCloseDt() == null || e.getCloseDt().isEmpty() ? "~ 채용시까지" : e.getCloseDt(),
                e.getUrl(),
                e.getRegDt()
        );
    }
}
