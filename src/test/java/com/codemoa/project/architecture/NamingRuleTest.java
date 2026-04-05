package com.codemoa.project.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * [테스터 담당] 클래스 명명 규칙 검증.
 * .cursorrules 패키지 구조 규칙에 따른 네이밍 컨벤션 자동 검증.
 */
@AnalyzeClasses(packages = "com.codemoa.project")
class NamingRuleTest {

    @ArchTest
    static final ArchRule rest_controllers_should_be_named_controller =
            classes()
                    .that().areAnnotatedWith(RestController.class)
                    .should().haveSimpleNameEndingWith("Controller")
                    .because("@RestController 클래스는 반드시 'Controller'로 끝나야 합니다.");

    @ArchTest
    static final ArchRule services_should_be_named_service =
            classes()
                    .that().areAnnotatedWith(Service.class)
                    .should().haveSimpleNameEndingWith("Service")
                    .orShould().haveSimpleNameEndingWith("ServiceImpl")
                    .because("@Service 클래스는 반드시 'Service' 또는 'ServiceImpl'로 끝나야 합니다.");

    @ArchTest
    static final ArchRule jpa_repositories_should_be_named_repository =
            classes()
                    .that().areAssignableTo(JpaRepository.class)
                    .and().areInterfaces()
                    .should().haveSimpleNameEndingWith("Repository")
                    .because("JpaRepository 인터페이스는 반드시 'Repository'로 끝나야 합니다.");

    @ArchTest
    static final ArchRule request_dtos_should_reside_in_request_package =
            classes()
                    .that().haveSimpleNameEndingWith("Request")
                    .and().resideInAPackage("..dto..")
                    .should().resideInAPackage("..dto.request..")
                    .because("Request DTO는 dto/request 패키지에 위치해야 합니다.");

    @ArchTest
    static final ArchRule response_dtos_should_reside_in_response_package =
            classes()
                    .that().haveSimpleNameEndingWith("Response")
                    .and().resideInAPackage("..dto..")
                    .should().resideInAPackage("..dto.response..")
                    .because("Response DTO는 dto/response 패키지에 위치해야 합니다.");
}
