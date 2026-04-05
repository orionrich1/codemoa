package com.codemoa.project.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

/**
 * [테스터 담당] 레이어 간 의존 방향 검증.
 * .cursorrules 금지 사항 3번(Controller→Repository 직접 접근 금지),
 * 2번(Service→Controller 의존 금지) 자동 검증.
 */
@AnalyzeClasses(packages = "com.codemoa.project")
class LayeringRuleTest {

    @ArchTest
    static final ArchRule layered_architecture_rule =
            layeredArchitecture().consideringAllDependencies()
                    .layer("Controller").definedBy("..controller..")
                    .layer("Service").definedBy("..service..")
                    .layer("Repository").definedBy("..repository..")
                    .layer("Entity").definedBy("..entity..")
                    .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                    .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Service")
                    .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");

    @ArchTest
    static final ArchRule controller_must_not_directly_access_repository =
            noClasses()
                    .that().resideInAPackage("..controller..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..repository..")
                    .because(".cursorrules 규칙: Controller는 반드시 Service를 통해서만 데이터에 접근해야 합니다.");

    @ArchTest
    static final ArchRule service_must_not_depend_on_controller =
            noClasses()
                    .that().resideInAPackage("..service..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..controller..")
                    .because(".cursorrules 규칙: Service는 Controller에 의존할 수 없습니다.");
}
