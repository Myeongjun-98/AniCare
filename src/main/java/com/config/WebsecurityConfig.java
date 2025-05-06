package com.config;

import com.service.medical.devUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebsecurityConfig {

    private final devUserService devUserService;


    // (Medical) 개발용 유저
    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder pwEnc) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(devUserService);  // DB 기반 서비스
        provider.setPasswordEncoder(pwEnc);
        return provider;
    }// (Medicacl) 개발용 유저

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           // >>  개발용 유저
                                           DaoAuthenticationProvider authProvider
                                           // <<  개발용 유저
    ) throws Exception {

        http
                // >> 개발용 유저
                // ① DB 기반 인증 프로바이더 등록
                .authenticationProvider(authProvider)
                // << 개발용 유저
                .authorizeHttpRequests(
                        ar -> ar
                                .requestMatchers("/community/**", "/medical/**",
                                        // >> (Medical)개발용 유저 등록을 위해 삽입
                                        // (Medical)로그인 페이지와 정적 리소스 모두 허용
                                        "/login", "/css/**", "/js/**", "/images/**")
                                // << (Medical)개발용 유저 등록을 위해 삽입
                                .permitAll()
                                // >> (Medical)개발용 유저 등록을 위해 삽입
                                .anyRequest().authenticated()
                        // << (Medical)개발용 유저 등록을 위해 삽입
                )
                // >> (Medical)개발용 유저 등록을 위해 삽입
                // 2) 로그인 폼 설정
                .formLogin(form -> form
                        .loginPage("/loginmedi")
                        .defaultSuccessUrl("/medical/medicalMain", true)
                        .permitAll()
                )
                // 3) 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/logoutmedi")
                        .logoutSuccessUrl("/loginmedi?logoutmedi")
                        .permitAll()
                );
        // << (Medical)개발용 유저 등록을 위해 삽입
        return http.build();
    }

    // 개발자 로그인을 위해 암호화 기능 정지
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    // 개발자용 로그인을 위한 암호화 없이 로그인하기////////////////
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
/// ///////////////////////////////////

}
