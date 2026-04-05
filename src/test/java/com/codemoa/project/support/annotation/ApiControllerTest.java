package com.codemoa.project.support.annotation;

import com.codemoa.project.support.config.TestSecurityConfig;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @RestController 슬라이스 테스트용 메타 어노테이션.
 * MockMvc를 통해 HTTP 요청/응답, 직렬화, 유효성 검사를 검증한다.
 * Spring Security는 TestSecurityConfig로 대체하여 인증을 단순화한다.
 *
 * 사용 예:
 * <pre>
 * {@literal @}ApiControllerTest(CommunityBoardController.class)
 * class CommunityBoardControllerTest {
 *     {@literal @}Autowired MockMvc mockMvc;
 *     {@literal @}MockBean CommunityBoardService boardService;
 * }
 * </pre>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@WebMvcTest
@Import(TestSecurityConfig.class)
public @interface ApiControllerTest {
}
