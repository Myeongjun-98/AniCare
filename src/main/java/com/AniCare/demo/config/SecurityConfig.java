package com.AniCare.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache=new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        http.requestCache(rq->rq.requestCache(requestCache))
                .authorizeHttpRequests(user -> user
                        .requestMatchers("/","/anicare","/anicare/**","/mainpage","/signup","/image/**","/anicareFile/**","/css/**", "/javascript/**","/mypage").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form
                                .loginPage("/mainpage/userlogin")  // 커스텀 로그인 페이지 주소
                                .defaultSuccessUrl("/") // 로그인 성공하면 어디로 ?
                                .usernameParameter("email") //로그인 할때 계정명 input name
                                .failureUrl("/signIn/error")// 로그인 실패시 어디로?
                                .permitAll()  // 로그인 페이지 에 대한 모두가 접근할수 있게 허용
                )
                .logout(out->out
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                );


        // 마이페이지
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/mypage/**").authenticated() // 마이페이지는 인증 필요
//                        .anyRequest().permitAll()
//                )
//                .formLogin(form -> form
//                        .loginPage("/mainpage/userlogin")
//                        .defaultSuccessUrl("/mypage")
//                );


        http.csrf(
                cr ->
                        cr.csrfTokenRepository(
                                CookieCsrfTokenRepository.withHttpOnlyFalse()));
        //http.formLogin(Customizer.withDefaults());


        return http.build();
    }





}
