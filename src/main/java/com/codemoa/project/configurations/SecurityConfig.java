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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

import com.codemoa.project.domain.user.security.CustomLoginSuccessHandler;
import com.codemoa.project.domain.user.security.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
@org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
public class SecurityConfig {

	@Lazy
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	
    private final CustomLoginSuccessHandler customLoginSuccessHandler;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(@Lazy CustomLoginSuccessHandler customLoginSuccessHandler,
            UserDetailsService userDetailsService) {
        this.customLoginSuccessHandler = customLoginSuccessHandler;
        this.userDetailsService = userDetailsService;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
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
            // CSRF: 쿠키 기반 활성화.
            // CookieCsrfTokenRepository + CsrfTokenRequestAttributeHandler 조합 사용.
            // XorCsrfTokenRequestAttributeHandler(지연 로딩)와 조합하면 GET 응답에
            // Set-Cookie 헤더가 누락되어 POST 시 403이 발생하므로 사용 금지.
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                .ignoringRequestMatchers(
                    // 체크리스트·다이어리 REST API
                    "/my-pages/updateCheckBox",
                    "/my-pages/addChecklist",
                    "/my-pages/updateChecklist",
                    "/my-pages/deleteChecklist",
                    "/my-pages/saveDiary",
                    "/my-pages/deleteDiary/**",
                    // AI 문제 REST API
                    "/problems/apiRequest",
                    "/problems/apiQuestion",
                    "/problems/listUpdate",
                    // 채용 REST API
                    "/employmentfetch",
                    "/employment/crawl",
                    // 커뮤니티 REST API
                    "/api/**",
                    // 정보 추천 REST API
                    "/information/**",
                    // 소켓
                    "/ws/**"
                )
            )

            // HTTP 요청 권한 설정
            .authorizeHttpRequests(auth -> auth
                // 관리자 페이지는 'ADMIN' 역할이 필요합니다.
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // 마이페이지는 로그인이 필요합니다.
                .requestMatchers("/my-pages/**").authenticated()
                
                // 랭킹페이지는 로그인이 필요합니다.
                .requestMatchers("/ranking/**", "/ranking").authenticated()

                // AI API 엔드포인트: 로그인 필요 (과금 방지)
                .requestMatchers("/problems/apiRequest", "/problems/apiQuestion").authenticated()

                // 채용 크롤링 엔드포인트: ADMIN 전용
                .requestMatchers("/employmentfetch", "/employment/crawl").hasRole("ADMIN")

                // 글쓰기·수정·삭제 페이지는 로그인이 필요합니다.
                .requestMatchers("/community/*/write", "/community/*/*/edit").authenticated()

                // 커뮤니티 데이터 변경 API
                .requestMatchers(HttpMethod.POST, "/api/boards", "/api/boards/*/comments", "/api/comments/*/adopt").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/boards/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/boards/**").authenticated()

                // 나머지는 모두 허용
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
            )

            .rememberMe(remember -> remember
                    .key("codemoa-remember-me")
                    .tokenValiditySeconds(14 * 24 * 60 * 60)
                    .userDetailsService(userDetailsService)
            );

        return http.build();
    }
}