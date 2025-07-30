package com.codemoa.project.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codemoa.project.domain.user.entity.PointLog;

public interface PointLogRepository extends JpaRepository<PointLog, Integer> {
    // JpaRepository<PointLog, Integer>
}