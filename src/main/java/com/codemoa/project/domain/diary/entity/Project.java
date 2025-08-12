//도영
package com.codemoa.project.domain.diary.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "project")
public class Project {

    public enum Status {
        계획, 진행중, 종료
    }

    @Id
    @Column(name = "project_id", nullable = false, length = 10)
    private Integer projectId;

    @Column(name = "user_id", nullable = false, length = 10)
    private String userId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.계획;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();    
    
}