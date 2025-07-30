package com.codemoa.project.domain.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codemoa.project.domain.community.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByCommunityBoard_BoardNo(Integer boardNo);
}