package com.example.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // 모든 url 에 대하여 인증 상관없이 사용할 수 있다.
        http.authorizeHttpRequests().antMatchers("/**").permitAll();
        http.headers().frameOptions().disable();
        http.formLogin().disable();

        return http.build();
    }
}
