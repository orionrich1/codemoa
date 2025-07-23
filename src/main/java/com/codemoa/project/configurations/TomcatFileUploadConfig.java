package com.codemoa.project.configurations;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatFileUploadConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
        return (factory) -> factory.addConnectorCustomizers(connector ->
            connector.setProperty("fileCountMax", "-1")); // 파일 파트 개수 제한 100개
        	
    }
}