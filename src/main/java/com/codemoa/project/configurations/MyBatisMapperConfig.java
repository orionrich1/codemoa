package com.codemoa.project.configurations;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * {@code @SpringBootApplication}에 두면 슬라이스 테스트({@code @DataJpaTest}, {@code @WebMvcTest})가
 * 메인 설정 클래스를 불러올 때 Mapper 빈까지 등록하려다 SqlSessionFactory 없이 실패한다.
 * 매퍼 스캔만 이 설정으로 분리한다.
 */
@Configuration
@MapperScan("com.codemoa.project.domain.**.mapper")
public class MyBatisMapperConfig {
}
