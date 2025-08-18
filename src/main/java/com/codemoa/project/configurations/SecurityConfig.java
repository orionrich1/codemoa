package com.codemoa.project.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.codemoa.project.domain.user.security.CustomLoginSuccessHandler;
import com.codemoa.project.domain.user.security.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Lazy
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	
    private final CustomLoginSuccessHandler customLoginSuccessHandler;

    public SecurityConfig(@Lazy CustomLoginSuccessHandler customLoginSuccessHandler) {
        this.customLoginSuccessHandler = customLoginSuccessHandler;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt를 기본으로 사용하면서, {noop} 등 다른 방식도 지원하도록 설정
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF 보호 비활성화
            .csrf(csrf -> csrf.disable())

            // HTTP 요청 권한 설정
            .authorizeHttpRequests(auth -> auth
                // 관리자 페이지는 'ADMIN' 역할이 필요합니다.
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // 마이페이지는 로그인이 필요합니다.
                .requestMatchers("/my-pages/**").authenticated()
                
                // 랭킹페이지는 로그인이 필요합니다.
                .requestMatchers("/ranking**").authenticated()

                // [핵심 추가] 모든 글쓰기 및 수정 페이지는 로그인이 필요합니다.
                // /community/*/write -> 'Java', 'free' 등 모든 카테고리의 글쓰기 페이지를 의미합니다.
                .requestMatchers("/community/*/write", "/community/*/*/edit").authenticated()

                // 글쓰기, 수정, 삭제 등 데이터 변경 API는 로그인이 필요합니다. (API 경로는 그대로 유지)
                .requestMatchers(HttpMethod.POST, "/api/boards", "/api/boards/*/comments", "/api/comments/*/adopt").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/boards/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/boards/**").authenticated()

                // 위에서 정의한 제한 외 "나머지는 모두 허용"합니다.
                .anyRequest().permitAll()
            )
            
            // 폼 로그인 및 SNS 로그인 설정
            .formLogin(form -> form
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")     
                .successHandler(customLoginSuccessHandler)
                .permitAll()
            )
        	.oauth2Login(oauth2 -> oauth2
    	        .loginPage("/loginForm") 
    	        .userInfoEndpoint(userInfo -> userInfo
    	            .userService(customOAuth2UserService) 
    	        )
    	        .successHandler(customLoginSuccessHandler)
    	        .failureHandler((request, response, exception) -> {
    	            if (exception.getMessage().contains("회원가입 필요")) {
    	                response.sendRedirect("/loginForm");
    	            } else {
    	                response.sendRedirect("/loginForm?error");
    	            }
    	        })
    	    )
        
            // 로그아웃 설정
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginForm")
                .invalidateHttpSession(true)
            );

        return http.build();
    }
}