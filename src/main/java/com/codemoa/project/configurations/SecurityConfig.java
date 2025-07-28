package com.codemoa.project.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 1. CSRF 보호 설정: /api/** 경로는 비활성화, 나머지는 활성화
        http.csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/**")
        );

        // 2. HTTP 요청에 대한 접근 권한 설정 (이하 동일)
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**", "/loginForm", "/login", "/css/**", "/js/**").permitAll()
                .requestMatchers("/mypage/**").authenticated()
                .anyRequest().permitAll()
        );

        // 3. 폼 로그인 설정 (이하 동일)
        http.formLogin(form -> form
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .permitAll()
        );

        // 4. 로그아웃 설정 (이하 동일)
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginForm")
                .invalidateHttpSession(true)
        );

        return http.build();
    }
}
