package com.codemoa.project.domain.employment.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // 이걸 추가해야 @Scheduled가 동작합니다!
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}