package com.AniCare.demo.config;

import com.AniCare.demo.service.mainpage.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 요청 캐시 설정 (중복 요청 방지)
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);

        http
                .requestCache(rq -> rq.requestCache(requestCache))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/anicare", "/anicare/**",
                                "/mainpage", "/mainpage/**",
                                "/signup", "/image/**", "/anicareFile/**", "/community/**",
                                "/css/**", "/javascript/**", "/mypage","/search","/search/**").permitAll()
                        .requestMatchers("/ad/**").hasAuthority("ADMIN")  // ✅ Authorization 기반 권한 체크
                        .requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/mainpage/userlogin")                    // 로그인 페이지
                        .usernameParameter("email")                         // 이메일로 로그인
                        .defaultSuccessUrl("/anicare", true)                // 로그인 성공 후 이동
                        .failureUrl("/mainpage/userlogin?error=true")       // 실패 시 다시 로그인
                        .permitAll()
                )
                .logout(out -> out
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/anicare")
                        .invalidateHttpSession(true)
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
