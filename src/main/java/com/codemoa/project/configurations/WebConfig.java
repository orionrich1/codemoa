package com.codemoa.project.configurations;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // [추가] application.properties 파일의 'file.upload.dir' 값을 주입받습니다.
    // 이렇게 하면 로컬과 서버의 경로 설정을 코드 수정 없이 변경할 수 있습니다.
    @Value("${file.upload.dir}")
    private String uploadDir;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 기존에 있던 코드는 그대로 둡니다.
    }

    // [수정] addResourceHandlers 메서드를 아래 내용으로 완전히 교체합니다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String normalized = uploadDir.replace('\\', '/');
        if (!normalized.endsWith("/")) {
            normalized = normalized + "/";
        }
        registry.addResourceHandler("/resources/files/**")
                .addResourceLocations("file:///" + normalized);

        // 정보 영역 썸네일: 업로드 디렉터리 + (선택) 클래스패스 정적 리소스
        Path informationDir = Paths.get(uploadDir, "information").normalize();
        String informationLocation = informationDir.toUri().toString();
        if (!informationLocation.endsWith("/")) {
            informationLocation = informationLocation + "/";
        }
        registry.addResourceHandler("/files/information/**")
                .addResourceLocations(informationLocation, "classpath:/static/files/information/");
    }
}