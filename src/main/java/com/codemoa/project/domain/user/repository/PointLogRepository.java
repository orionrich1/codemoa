package com.codemoa.project.domain.user.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.codemoa.project.domain.ranking.dto.response.WeeklyPointSummary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import java.util.List;


import com.codemoa.project.domain.user.entity.PointEventType;
import com.codemoa.project.domain.user.entity.PointLog;
import com.codemoa.project.domain.user.entity.User;

public interface PointLogRepository extends JpaRepository<PointLog, Integer> {
    // JpaRepository<PointLog, Integer>
	boolean existsByUserAndEventTypeAndCreatedAtAfter(User user, PointEventType eventType, LocalDateTime dateTime);

	@Query("SELECT new com.codemoa.project.domain.ranking.dto.response.WeeklyPointSummary(pl.user.userId, SUM(pl.points)) " +
		       "FROM PointLog pl " +
		       "WHERE pl.createdAt >= :startDate " +
		       "GROUP BY pl.user.userId " +
		       "ORDER BY SUM(pl.points) DESC")
		List<WeeklyPointSummary> findWeeklyTopRankers(@Param("startDate") LocalDateTime startDate, Pageable pageable);


}