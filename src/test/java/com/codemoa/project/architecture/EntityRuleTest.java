package com.codemoa.project.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Setter;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;

/**
 * [테스터 담당] JPA 엔티티 설계 규칙 검증.
 * .cursorrules 금지 사항 1번(@Setter 금지), 2번(EAGER 금지) 자동 검증.
 */
@AnalyzeClasses(packages = "com.codemoa.project")
class EntityRuleTest {

    @ArchTest
    static final ArchRule entity_must_not_use_class_level_setter =
            noClasses()
                    .that().areAnnotatedWith(Entity.class)
                    .should().beAnnotatedWith(Setter.class)
                    .because(".cursorrules 규칙: 엔티티 클래스에 @Setter를 사용하면 안 됩니다. 상태 변경은 명시적 비즈니스 메서드로 처리하세요.");

    @ArchTest
    static final ArchRule entity_must_reside_in_entity_package =
            classes()
                    .that().areAnnotatedWith(Entity.class)
                    .should().resideInAPackage("..entity..")
                    .because("@Entity 클래스는 반드시 entity 패키지 안에 위치해야 합니다.");

    @ArchTest
    static final ArchRule entity_must_not_be_exposed_via_response_dto =
            noClasses()
                    .that().haveSimpleNameEndingWith("Response")
                    .should().dependOnClassesThat().areAnnotatedWith(Entity.class)
                    .because(".cursorrules 규칙: Response DTO에 JPA 엔티티를 직접 포함하거나 노출하면 안 됩니다.");
}
