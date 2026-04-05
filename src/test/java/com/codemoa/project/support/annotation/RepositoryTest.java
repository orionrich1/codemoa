package com.codemoa.project.support.annotation;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JPA Repository 슬라이스 테스트용 메타 어노테이션.
 * 실제 MySQL 데이터소스를 사용하며(Replace.NONE), JPA 레이어만 로드한다.
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
