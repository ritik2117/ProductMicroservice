package com.scaler.productmicroservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        try {
            http
                    /*.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
                    .cors(cors -> cors.disable()) // Updated approach for disabling CORS
                    .csrf(csrf -> csrf.disable()); // Updated approach for disabling CSRF*/
                    .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/products/{id}").hasAuthority("SCOPE_ADMIN")
//                            .anyRequest().permitAll()
                                    .anyRequest().authenticated()
                    )
                    .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return http.build();
    }
}
