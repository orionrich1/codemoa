//도영
package com.codemoa.project.domain.diary.dto.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveDiaryRequest {
	private int projectId;
	private int diaryId;
	private String content;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate workDate;
}
