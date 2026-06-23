package com.example.myproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 현재 클래스를 설정 컴퍼넌트로 위임
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // SecurityFilterChain : 클라이언트로부터 오는 모든 요청/응답을
        // Filter 영역에서 검사하는 클래스
        // 인증, 인가, CSRF 설정, 로그인/로그아웃 방식 설정
        // HttpSecurity : 보안 규칙을 설정하는 객체

        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()
        )
        .formLogin(
            form -> form.loginPage("/auth/login") // 커스텀 로그인 페이지 경로 연결(/templates/auth/login.html 페이지를 의미)
                        .loginProcessingUrl("/auth/login") // login POST 처리 URL(form action 경로와 일치)
        )
        ;

        return http.build();
    }
 
    // 비밀번호 암호화 객체 관리
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
