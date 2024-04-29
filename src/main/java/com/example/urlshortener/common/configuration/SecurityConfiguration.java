package com.example.urlshortener.common.configuration;

import com.example.urlshortener.common.security.handler.CustomAuthenticationFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http
            .cors(cors -> {});
        // H2 DB 헤더 옵션
        http
            .headers(headers ->
                headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
                    XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)
                )
            );
        // Chapter 6.1 모든 요청에 HTTPS 적용하기
//        http
//            .requiresChannel(
//                requiresChannel -> requiresChannel.anyRequest().requiresSecure()
//            );
        http
            .authorizeHttpRequests(
                authorize -> authorize
                    // 로그인 보안 관련 엔드포인트는 모두 접근 허용 + 편의를 위해 나머지도 접근 허용
                    .requestMatchers("/adduser", "/login", "/login-error", "/login-locked").permitAll()
                    .requestMatchers("/webjars/**", "/static/css/**", "/h2-console/**", "/images/**").permitAll()
                    .anyRequest().permitAll()
//                    .anyRequest().authenticated()
            );
        http
            .formLogin(
                formLogin -> formLogin
                    .loginPage("/login")
                    .defaultSuccessUrl("/index", true).permitAll()
                    .failureHandler(customAuthenticationFailureHandler)
            );

        return http.build();
    }
}