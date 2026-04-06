package com.codemoa.project.domain.information.support;

import java.sql.Timestamp;

import com.codemoa.project.domain.information.dto.request.BookFormRequest;
import com.codemoa.project.domain.information.dto.request.ContestFormRequest;
import com.codemoa.project.domain.information.dto.request.LectureFormRequest;
import com.codemoa.project.domain.information.entity.Book;
import com.codemoa.project.domain.information.entity.Contest;
import com.codemoa.project.domain.information.entity.Lecture;

/**
 * 정보 도메인 폼 DTO → MyBatis용 POJO 조립.
 */
public final class InformationFormMapper {

	private InformationFormMapper() {
	}

	public static Lecture toLectureForInsert(LectureFormRequest r, String file1) {
		float rating = Float.parseFloat(trimToEmpty(r.getRating()));
		return new Lecture(0, r.getUserId(), r.getTitle(), r.getSubtitle(), r.getCategory(), rating, null,
				nullToEmpty(r.getLectureSource()), r.getContent1(), r.getContent2(), file1);
	}

	public static Lecture toLectureForUpdate(LectureFormRequest r, Timestamp regDate, String file1) {
		float rating = Float.parseFloat(trimToEmpty(r.getRating()));
		return new Lecture(r.getRecommendNo(), r.getUserId(), r.getTitle(), r.getSubtitle(), r.getCategory(), rating,
				regDate, nullToEmpty(r.getLectureSource()), r.getContent1(), r.getContent2(), file1);
	}

	public static Book toBookForInsert(BookFormRequest r, Timestamp pubDate, String file1) {
		float rating = Float.parseFloat(trimToEmpty(r.getRating()));
		int pages = Integer.parseInt(trimToEmpty(r.getTotalPageNum()));
		return new Book(0, r.getUserId(), r.getTitle(), r.getPublisher(), null, pubDate,
				nullToEmpty(r.getBookSource()), r.getContent(), file1, rating, trimToEmpty(r.getIsbn()), pages, 0);
	}

	public static Book toBookForUpdate(BookFormRequest r, Timestamp regDate, Timestamp pubDate, String file1,
			int viewCount) {
		float rating = Float.parseFloat(trimToEmpty(r.getRating()));
		int pages = Integer.parseInt(trimToEmpty(r.getTotalPageNum()));
		return new Book(r.getBookNo(), r.getUserId(), r.getTitle(), r.getPublisher(), regDate, pubDate,
				nullToEmpty(r.getBookSource()), r.getContent(), file1, rating, trimToEmpty(r.getIsbn()), pages, viewCount);
	}

	public static Contest toContestForInsert(ContestFormRequest r, Timestamp start, Timestamp end, String file1) {
		return new Contest(0, r.getUserId(), r.getTitle(), r.getHostOrganization(), null, start, end,
				nullToEmpty(r.getContestSource()), r.getContent(), null, file1, 0);
	}

	public static Contest toContestForUpdate(ContestFormRequest r, Timestamp regDate, Timestamp start, Timestamp end,
			String file1, String pass, int viewCount) {
		return new Contest(r.getContestNo(), r.getUserId(), r.getTitle(), r.getHostOrganization(), regDate, start, end,
				nullToEmpty(r.getContestSource()), r.getContent(), pass, file1, viewCount);
	}

	private static String nullToEmpty(String s) {
		return s == null ? "" : s;
	}

	private static String trimToEmpty(String s) {
		return s == null ? "" : s.trim();
	}
}
