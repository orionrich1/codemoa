
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
    private String location;
    private String url;         // 상세 페이지 URL
    private String deadline;
    
    public static EmploymentDto fromEntity(Employment e) {
        return new EmploymentDto(
                e.getRecruitNo(),
                e.getTitle(),
                e.getCompany(),
                e.getRegion() + " " + e.getSubRegion(),
                e.getUrl(),
                e.getCloseDt() == null || e.getCloseDt().isEmpty() ? "~ 채용시까지" : e.getCloseDt()
        );
    }
}
    
