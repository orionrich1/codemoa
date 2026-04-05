package com.codemoa.project.support.fixture;

import com.codemoa.project.domain.recruit.entity.TeamRecruit;

import java.time.LocalDateTime;

/**
 * TeamRecruit 엔티티 테스트 데이터 팩토리.
 */
public class TeamRecruitFixture {

    public static TeamRecruit create(String userId) {
        TeamRecruit recruit = new TeamRecruit();
        recruit.setUserId(userId);
        recruit.setTitle("2026 해커톤 팀원 모집");
        recruit.setContent("열정 있는 팀원을 모집합니다.");
        recruit.setRecruitType("공모전");
        recruit.setRecruitPeriod("2주");
        recruit.setActivityPeriod("3개월");
        recruit.setTotalMembers(4);
        recruit.setRemainingMembers(2);
        recruit.setProgressType("온라인");
        recruit.setContact("kakao_open_chat_link");
        recruit.setTechStack("#Java #SpringBoot");
        recruit.setStatus("모집중");
        recruit.setRegDate(LocalDateTime.now());
        return recruit;
    }

    public static TeamRecruit createClosed(String userId) {
        TeamRecruit recruit = create(userId);
        recruit.setStatus("모집마감");
        recruit.setRemainingMembers(0);
        return recruit;
    }
}
