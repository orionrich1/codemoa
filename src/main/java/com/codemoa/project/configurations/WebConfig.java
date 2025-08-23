package com.codemoa.project.configurations;

// [수정] @Value 어노테ATION을 사용하기 위해 import 추가
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
        // 1. 웹 요청 주소를 HTML에서 사용하는 '/files/**'로 수정했습니다.
        // 2. 실제 파일 위치를 @Value로 주입받은 uploadDir 변수를 사용하도록 변경했습니다.
        // 3. 경로 형식을 'file:///'로 수정하여 Windows와 Linux 양쪽에서 안정적으로 동작하도록 했습니다.
    	registry.addResourceHandler("/resources/files/**")
        .addResourceLocations("file:///" + uploadDir);
    }
}