package com.codemoa.project.domain.recruit.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamRecruit {

    private int recruitId;      // recruit_id (PK)
    private String userId;      // user_id (FK, 작성자)
    private String contestTitle;	//공모전 url정보
    private String recruitType;	//모집 분류 (팀원구인 / 팀원으로 참가희망)
    private String recruitPeriod;	//모집 기간
    private String activityPeriod;	//활동 기간
    private int totalMembers;		//모집 희망 인원
    private int remainingMembers;		//남은 인원
    private String progressType;		//진행 방식 (온라인 /오프라인/ 혼합)
    private String contact;			//연락 방법
    private String techStack;		//기술 스택(#태그)
    private String status;      // 모집 상태 (예: 모집중, 모집완료)
    private String applyGuide;	//지원 방법 (포트폴리오, 간단한 자기소개서 등)
    private String title;				//게시글 제목
    private String content;			//내용
    private String attachmentUrl; //첨부파일
    private int viewCount;			//열람 횟수 기록
    private LocalDateTime regDate;		// 작성 시간
    private LocalDateTime updateDate;		//수정일
    private String statusName;
    private String recruitTypeName;
    private String nickname;
    // --- Getters and Setters ---

    public String getRecruitTypeName() {
    	if("TEAM_RECRUIT".equals(this.recruitType)) {
    		return "팀원 모집";
    	} else if("TEAM_JOIN".equals(this.recruitType)) {
    		return "참가 희망";
    	} else {
    		return "미정";
    	}
    }
    
    public String getStatusName() {
    	if("RECRUITING".equals(this.status)) {
    		return "모집중";
    	} else if("COMPLETED".equals(this.status)) {
    		return "모집 완료";
    	} else {
    		return "미정";
    	}
    }
    
}