package com.codemoa.project.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codemoa.project.domain.user.entity.SnsUser;

public interface SnsUserRepository extends JpaRepository<SnsUser, String> {

	Optional<SnsUser> findBySnsId(String snsId);
}
