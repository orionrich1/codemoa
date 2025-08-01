// src/main/java/com/codemoa/project/domain/user/entity/UserGrade.java
package com.codemoa.project.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_grade")
public class UserGrade {

    @Id
    @Column(name = "grade_id", nullable = false, length = 30)
    private String gradeId;

    @Column(name = "grade_name", nullable = false, length = 30)
    private String gradeName;

    @Column(name = "min_points", nullable = false)
    private Integer minPoints;

    /**
     * DataInitializer에서 테스트용 등급을 생성하기 위해 추가된 생성자입니다.
     */
    public UserGrade(String gradeId, String gradeName, Integer minPoints) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.minPoints = minPoints;
    }
}
