package com.codemoa.project.domain.user.repository;

import com.codemoa.project.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    // 아이디로 사용자를 찾는 메서드 (중복 체크용)
    Optional<User> findByUserId(String userId); 
}