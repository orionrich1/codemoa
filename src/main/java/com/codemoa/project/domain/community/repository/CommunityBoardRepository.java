package com.codemoa.project.domain.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codemoa.project.domain.community.entity.CommunityBoard;

public interface CommunityBoardRepository extends JpaRepository<CommunityBoard, Integer> {
    
}