package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebsecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .csrf(csrf -> csrf.disable()) //실제 테스트 때에는 비활성화할 것.
                .authorizeHttpRequests(
                ar -> ar
                        .requestMatchers("/", "/community/**", "/anicareFile/**")
                        .permitAll()
                        .requestMatchers( "/css/**", "/javascript/**", "/error")
                        .permitAll()

        );



        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}


}
