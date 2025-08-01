package com.codemoa.project.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                // 1. (가장 구체적인 규칙) /admin/** 경로는 'ADMIN' 역할을 가진 사용자만 접근 가능
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // 2. (로그인 필요한 규칙) /mypage/** 등 로그인이 필요한 경로는 인증된 사용자만
                .requestMatchers("/my-pages/**").authenticated()
                
                // 3. (가장 마지막 규칙) 위에서 설정한 경로 외 나머지 모든 경로는 모두에게 허용
                .anyRequest().permitAll()
        );

        // 폼 로그인 및 로그아웃 설정은 기존과 동일합니다.
        http
        	.formLogin(form -> form
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")                
                .successHandler(customLoginSuccessHandler)
                .permitAll()
        	)
        	
        	// SNS 로그인 시 실행되는 메소드
        	.oauth2Login(oauth2 -> oauth2
    	        .loginPage("/loginForm") 
    	        .userInfoEndpoint(userInfo -> userInfo
    	            .userService(customOAuth2UserService) 
    	        )
    	        // 성공 시 Handler 실행, 만약 SNS 계정이랑 연동된 상태면, 로그인한 계정과 SNS 계정을 연동함
    	        .successHandler(customLoginSuccessHandler)
    	        .failureHandler((request, response, exception) -> {
    	            if (exception.getMessage().contains("회원가입 필요")) {
    	                response.sendRedirect("/loginForm");
    	            } else {
    	                response.sendRedirect("/loginForm?error");
    	            }
    	        })
    	    );
        
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginForm")
                .invalidateHttpSession(true)
        );

        // 403 권한에러 해결
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}