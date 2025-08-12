//도영
package com.codemoa.project.domain.diary.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "project_checklist")
public class ProjectChecklist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "checklist_id")
	private Integer checklistId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;

	@Column(nullable = false, length = 255)
	private String content;

	private Boolean done;

	@Column(nullable = false)
	private Integer priority = 3;
}