//도영
package com.codemoa.project.domain.diary.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "project_checklist")
public class ProjectChecklist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer checklistId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@Column(nullable = false, length = 255)
	private String content;

	private Boolean done;

	@Column(nullable = false)
	private Integer priority = 3;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();	
}