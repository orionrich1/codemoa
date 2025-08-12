//도영
package com.codemoa.project.domain.diary.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "project_diary",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"project_id", "work_date"})})
public class ProjectDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Integer diaryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

    @Column(columnDefinition = "TEXT")
    private String content;
}