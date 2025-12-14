package com.example.aitouristguide.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/admins/**").permitAll() //for now
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passEncoder() {
        return new BCryptPasswordEncoder();
    }
}
