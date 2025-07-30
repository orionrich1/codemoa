//도영
package com.codemoa.project.domain.diary.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.codemoa.project.domain.user.entity.User;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "diary")
public class Diary {

	@Id
	@Column(name = "diary_no")
	private Integer diaryNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "project_name", length = 255)
	private String projectName;

	@Column(name = "git_url", length = 1024)
	private String gitUrl;

	@Column(name = "git_version", length = 100)
	private String gitVersion;

	@Column(length = 1000)
	private String commits;

	@Column(columnDefinition = "TEXT")
	private String history;

	@Column(columnDefinition = "TEXT")
	private String content;

	@Column(name = "reg_date", nullable = false, updatable = false)
	private LocalDateTime regDate;

	@PrePersist
	public void prePersist() {
		if (regDate == null) {
			regDate = LocalDateTime.now();
		}
	}
}