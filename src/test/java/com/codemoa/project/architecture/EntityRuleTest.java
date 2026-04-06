package com.codemoa.project.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * [테스터 담당] JPA 엔티티 설계 규칙 검증.
 * 참고: Lombok {@code @Setter}는 Retention SOURCE라 컴파일 후 바이트코드에 남지 않아 ArchUnit으로 검사할 수 없음.
 * 해당 규칙은 코드 리뷰·테스터 수동 검토로 보완한다.
 */
@AnalyzeClasses(packages = "com.codemoa.project")
class EntityRuleTest {

    @ArchTest
    static final ArchRule entity_must_reside_in_entity_package =
            classes()
                    .that().areAnnotatedWith(Entity.class)
                    .should().resideInAPackage("..entity..")
                    .because("@Entity 클래스는 반드시 entity 패키지 안에 위치해야 합니다.");

    @ArchTest
    @ArchIgnore(reason = "BoardDetailResponse 등이 엔티티 기반 생성자를 사용 중. DTO 분리 리팩터링 후 제거 예정.")
    static final ArchRule entity_must_not_be_exposed_via_response_dto =
            noClasses()
                    .that().haveSimpleNameEndingWith("Response")
                    .should().dependOnClassesThat().areAnnotatedWith(Entity.class)
                    .because(".cursorrules 규칙: Response DTO에 JPA 엔티티를 직접 포함하거나 노출하면 안 됩니다.");
}
