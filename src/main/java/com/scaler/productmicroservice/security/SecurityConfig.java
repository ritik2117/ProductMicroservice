package com.scaler.productmicroservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        try {
            http
                    .authorizeHttpRequests((requests) -> requests.anyRequest().permitAll())
                    .cors(cors -> cors.disable()) // Updated approach for disabling CORS
                    .csrf(csrf -> csrf.disable()); // Updated approach for disabling CSRF
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return http.build();
    }
}
