package com.codemoa.project.support.annotation;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Service 레이어 단위 테스트용 메타 어노테이션.
 * Spring 컨텍스트 없이 Mockito만 사용하여 빠른 단위 테스트를 수행한다.
 *
 * 사용 예:
 * <pre>
 * {@literal @}ServiceTest
 * class CommunityBoardServiceTest {
 *     {@literal @}InjectMocks CommunityBoardService sut;
 *     {@literal @}Mock CommunityBoardRepository boardRepository;
 * }
 * </pre>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(MockitoExtension.class)
public @interface ServiceTest {
}
