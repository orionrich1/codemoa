package com.codemoa.project.domain.main.support;

import com.codemoa.project.domain.employment.dto.response.EmploymentDto;
import com.codemoa.project.domain.information.entity.Book;
import com.codemoa.project.domain.information.entity.Contest;
import com.codemoa.project.domain.information.entity.Lecture;
import com.codemoa.project.domain.ranking.dto.response.UserRankingResponse;
import com.codemoa.project.domain.recruit.entity.TeamRecruit;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DB가 비었을 때 메인 레이아웃이 허전하지 않도록 쓰는 임시 샘플 데이터입니다.
 * 실제 데이터가 있으면 컨트롤러에서 사용하지 않습니다.
 */
public final class MainPageDemoData {

	private MainPageDemoData() {
	}

	private static LocalDateTime t(int daysAgo, int hour) {
		return LocalDateTime.now().minusDays(daysAgo).withHour(hour).withMinute(0).withSecond(0).withNano(0);
	}

	private static Timestamp tsFuture(int days) {
		long ms = System.currentTimeMillis() + days * 86400000L;
		return new Timestamp(ms);
	}

	public static List<UserRankingResponse> weeklyRanking() {
		List<UserRankingResponse> list = new ArrayList<>();
		list.add(UserRankingResponse.forPreview(1, "코드모아", "다이아", 420, "diamond"));
		list.add(UserRankingResponse.forPreview(2, "스프링러", "플래티넘", 380, "platinum"));
		list.add(UserRankingResponse.forPreview(3, "자바칩", "골드", 310, "gold"));
		list.add(UserRankingResponse.forPreview(4, "리액트킹", "실버", 240, "silver"));
		list.add(UserRankingResponse.forPreview(5, "파이썬러", "브론즈", 190, "bronze"));
		return list;
	}

	public static List<EmploymentDto> employments() {
		List<EmploymentDto> list = new ArrayList<>();
		list.add(new EmploymentDto(null, "백엔드 개발자 (Java/Spring)", "테크스타트업 A", "서울", "강남", "정규", "", "~ 채용시까지", null, ""));
		list.add(new EmploymentDto(null, "프론트엔드 (React/TS)", "코드모아 파트너사", "경기", "판교", "정규", "", "~ 채용시까지", null, ""));
		list.add(new EmploymentDto(null, "풀스택 엔지니어", "SaaS 스튜디오", "서울", "마포", "정규", "", "~ 채용시까지", null, ""));
		list.add(new EmploymentDto(null, "데이터 엔지니어 (Python)", "핀테크 B", "서울", "여의도", "정규", "", "~ 채용시까지", null, ""));
		list.add(new EmploymentDto(null, "DevOps / 인프라", "클라우드 팀", "서울", "구로", "정규", "", "~ 채용시까지", null, ""));
		return list;
	}

	public static List<TeamRecruit> teamRecruits() {
		List<TeamRecruit> list = new ArrayList<>();
		list.add(new TeamRecruit(0, "demo", "", "TEAM_RECRUIT", "", "", 4, 2, "온라인", "", "Spring · React",
				"RECRUITING", "", "졸업 작품 팀원 2명 모집", "", "", 0, LocalDateTime.now(), null, "", "", ""));
		list.add(new TeamRecruit(0, "demo", "", "TEAM_RECRUIT", "", "", 3, 1, "혼합", "", "Python · FastAPI",
				"RECRUITING", "", "해커톤 팀 합류 희망", "", "", 0, LocalDateTime.now(), null, "", "", ""));
		list.add(new TeamRecruit(0, "demo", "", "TEAM_JOIN", "", "", 5, 5, "온라인", "", "Vue · Node",
				"RECRUITING", "", "사이드 프로젝트 팀 찾습니다", "", "", 0, LocalDateTime.now(), null, "", "", ""));
		return list;
	}

	public static List<Lecture> lectures() {
		List<Lecture> list = new ArrayList<>();
		Lecture a = new Lecture();
		a.setRecommendNo(0);
		a.setUserId("CodeMoa");
		a.setTitle("스프링 부트 REST API 입문");
		a.setFile1(null);
		a.setLectureSource(null);
		list.add(a);
		Lecture b = new Lecture();
		b.setRecommendNo(0);
		b.setUserId("CodeMoa");
		b.setTitle("Git 브랜치와 협업 기초");
		b.setFile1(null);
		b.setLectureSource(null);
		list.add(b);
		Lecture c = new Lecture();
		c.setRecommendNo(0);
		c.setUserId("CodeMoa");
		c.setTitle("SQL 인덱스 개념 한 번에 잡기");
		c.setFile1(null);
		c.setLectureSource(null);
		list.add(c);
		return list;
	}

	public static List<Book> books() {
		List<Book> list = new ArrayList<>();
		Book a = new Book();
		a.setBookNo(0);
		a.setTitle("클린 코드");
		a.setPublisher("프리렉");
		a.setFile1(null);
		list.add(a);
		Book b = new Book();
		b.setBookNo(0);
		b.setTitle("이펙티브 자바");
		b.setPublisher("인사이트");
		b.setFile1(null);
		list.add(b);
		Book c = new Book();
		c.setBookNo(0);
		c.setTitle("HTTP 완벽 가이드");
		c.setPublisher("인사이트");
		c.setFile1(null);
		list.add(c);
		return list;
	}

	public static List<Contest> contests() {
		List<Contest> list = new ArrayList<>();
		Contest a = new Contest();
		a.setContestNo(0);
		a.setTitle("2026 오픈소스 컨트리뷰톤");
		a.setHostOrganization("코드모아 예시");
		a.setEndDate(tsFuture(21));
		a.setFile1(null);
		list.add(a);
		Contest b = new Contest();
		b.setContestNo(0);
		b.setTitle("대학생 앱 개발 챌린지");
		b.setHostOrganization("예시 주최처");
		b.setEndDate(tsFuture(5));
		b.setFile1(null);
		list.add(b);
		Contest c = new Contest();
		c.setContestNo(0);
		c.setTitle("AI 해커톤 시즌 3");
		c.setHostOrganization("테크 컨소시엄");
		c.setEndDate(tsFuture(45));
		c.setFile1(null);
		list.add(c);
		return list;
	}
}
