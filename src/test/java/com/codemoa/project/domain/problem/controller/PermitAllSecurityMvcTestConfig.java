package com.codemoa.project.domain.problem.controller;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/** WebMvcTest에서 {@code user(...)} 인증이 Holder까지 올라가도록 최소 필터 체인만 로드한다. */
@TestConfiguration
@EnableWebSecurity
public class PermitAllSecurityMvcTestConfig {

	@Bean
	SecurityFilterChain permitAllTestChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
		return http.build();
	}
}
