package com.codemoa.project.support.annotation;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JPA Repository 슬라이스 테스트용 메타 어노테이션.
 * 활성 프로파일의 데이터소스(로컬은 보통 MySQL)를 사용한다. {@code Replace.ANY}로 임베디드 DB를 쓰면
 * {@code user} 테이블명 등 예약어 이슈가 있어, 기본은 실제 DB와 동일 스키마를 쓴다.
 *
 * 사용 예:
 * <pre>
 * {@literal @}RepositoryTest
 * class CommunityBoardRepositoryTest {
 *     {@literal @}Autowired CommunityBoardRepository sut;
 *     {@literal @}Autowired TestEntityManager em;
 * }
 * </pre>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public @interface RepositoryTest {
}
