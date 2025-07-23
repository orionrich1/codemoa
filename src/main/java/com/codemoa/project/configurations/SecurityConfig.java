package com.codemoa.project.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


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
                .requestMatchers("/mypage/**").authenticated()
                
                // 3. (가장 마지막 규칙) 위에서 설정한 경로 외 나머지 모든 경로는 모두에게 허용
                .anyRequest().permitAll()
        );

        // 폼 로그인 및 로그아웃 설정은 기존과 동일합니다.
        http.formLogin(form -> form
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginForm")
                .invalidateHttpSession(true)
        );

        return http.build();
    }
}