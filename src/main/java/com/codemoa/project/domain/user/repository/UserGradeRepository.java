package com.codemoa.project.domain.user.repository;

import com.codemoa.project.domain.user.entity.UserGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGradeRepository extends JpaRepository<UserGrade, String> {
}
