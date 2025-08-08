// CommunityBoardRepository.java
package com.codemoa.project.domain.community.repository;

import com.codemoa.project.domain.community.entity.CommunityBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CommunityBoardRepository extends JpaRepository<CommunityBoard, Integer>, JpaSpecificationExecutor<CommunityBoard> {

    // JpaSpecificationExecutor가 Specification을 사용하는 findAll(spec, pageable) 메서드를
    // 이미 제공하므로, 별도로 메서드를 선언할 필요가 없습니다.
}
