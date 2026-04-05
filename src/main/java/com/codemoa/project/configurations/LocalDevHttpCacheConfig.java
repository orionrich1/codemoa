package com.codemoa.project.configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 로컬에서 브라우저가 HTML을 강하게 캐시해 템플릿 수정이 Ctrl+F5로도 안 보이는 현상을 줄이기 위한 설정입니다.
 * (정적 파일 경로는 제외)
 */
@Configuration
@Profile("local")
public class LocalDevHttpCacheConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptor() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
				String path = request.getRequestURI();
				if (path.startsWith("/css/")
						|| path.startsWith("/js/")
						|| path.startsWith("/bootstrap/")
						|| path.startsWith("/images/")
						|| path.startsWith("/files/")
						|| "/favicon.ico".equals(path)) {
					return true;
				}
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Expires", "0");
				return true;
			}
		});
	}
}
