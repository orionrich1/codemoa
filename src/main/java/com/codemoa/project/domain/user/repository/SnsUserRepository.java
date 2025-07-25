package com.codemoa.project.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codemoa.project.domain.user.entity.SnsUser;

public interface SnsUserRepository extends JpaRepository<SnsUser, String> {

}
