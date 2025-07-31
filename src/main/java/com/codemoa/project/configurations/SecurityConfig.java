package com.codemoa.project.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
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
                // 1. 정적 리소스 및 부트스트랩 경로는 누구나 접근 가능하도록 최우선으로 설정
                .requestMatchers("/css/**", "/js/**", "/images/**", "/bootstrap/**", "/webjars/**", "/favicon.ico").permitAll()
                
                // 2. 공개적으로 접근 가능한 페이지 경로 설정
                .requestMatchers("/", "/loginForm", "/list", "/boards/**").permitAll()

                // 3. 게시판 API 중 GET 요청(조회)은 누구나 가능하도록 허용
                .requestMatchers(HttpMethod.GET, "/api/boards", "/api/boards/**").permitAll()
                
                // 4. 커뮤니티 게시판의 핵심 기능(쓰기, 수정, 삭제, 댓글 등)은 인증(로그인) 필요
                .requestMatchers(
                    "/api/boards", 
                    "/api/boards/**", 
                    "/api/boards/*/comments", 
                    "/api/comments/*/adopt"
                ).authenticated()

                // 5. 다른 팀원들이 작업한 기존 규칙 유지
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/my-pages/**").authenticated()
                
                // 6. 위에서 명시적으로 허용한 경로 외 나머지 모든 요청은 인증(로그인)을 요구
                .anyRequest().authenticated()
        );

        // 폼 로그인 및 SNS 로그인 설정 (기존 코드 유지)
        http
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
    	    );
        
        // 로그아웃 설정 (기존 코드 유지)
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginForm")
                .invalidateHttpSession(true)
        );

        // CSRF 보호 설정: API 경로는 비활성화하여 외부 클라이언트의 요청을 허용
        http.csrf(csrf -> csrf
            .ignoringRequestMatchers("/h2-console/**", "/api/**")
        );
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
