package com.codemoa.project.domain.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.codemoa.project.domain.user.entity.LocalUser;

public interface LocalUserRepository extends JpaRepository<LocalUser, String>{


}
