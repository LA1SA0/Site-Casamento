package com.igorfabricia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/galeria/**",
                                "/mensagens/**",
                                "/presentes/**"
                        ).permitAll()
                        .requestMatchers("/noivos/login").permitAll()
                        .requestMatchers("/convidados/**", "/noivos/**").authenticated()
                        .anyRequest().authenticated()
                )
                .with(new HttpBasicConfigurer<>(), basic -> {});
        return http.build();
    }
}


