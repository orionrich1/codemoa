package com.codemoa.project.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	/* 어떤 요청에 대해서 요청을 처리한 결과 데이터인 모델은 필요 없고 화면만
	 * 보여주는 경우 아래와 같이 addViewControllers() 메서드를 오버라이드하여
	 * 뷰 전용 컨트롤러를 설정할 수 있다. 
	 **/	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		/* 아래는 /writeForm과 /writeBoard 요청에 대한 뷰를 지정한 것이다.
		 * 여기서 지정한 뷰의 이름과 prefix, suffix를 조합하여 실제 뷰의
		 * 물리적인 이름이 결정된다.   
		 **/

		// 로그인 폼 뷰 전용 컨트롤러 설정 추가
		registry.addViewController("/loginForm").setViewName("views/user/loginForm");
	
		
	}

	/* addResourceHandlers() 메서드는 리소스 등록 및 핸들러를 관리하는 ResourceHandler
	 * Registry 객체를 통해서 리소스 URL과 이 URL에 매칭되는 리소스의 위치를 등록할 수 있다. 
	 **/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		/* 기존에 정적 리소스 핸들러의 설정은 그대로 유지하며 새로운 리소스 핸들러 추가
		 * /resources/** 로 요청되는 리소스 요청 설정 
		 **/
		registry.addResourceHandler("/resources/files/**")	
				// file: 프로토콜을 사용하면 업로드한 이미지가 바로 보인다. 
				.addResourceLocations("file:./src/main/resources/static/files/")
				.setCachePeriod(1); // 캐쉬 지속시간(초)
	}
	

}
