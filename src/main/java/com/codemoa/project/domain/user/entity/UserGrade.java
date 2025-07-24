package com.codemoa.project.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_grade") // ERD의 테이블명과 클래스명을 맞춰줍니다.
public class UserGrade {

    @Id
    @Column(name = "grade_id") // ERD의 컬럼명과 필드명을 매핑합니다.
    private String gradeId;

    @Column(name = "grade_name", nullable = false)
    private String gradeName;

    @Column(name = "min_points", nullable = false)
    private Integer minPoints;
}