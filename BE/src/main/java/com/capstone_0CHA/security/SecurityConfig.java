package com.capstone_0CHA.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable() // rest api를 사용할 것이므로 auth , csrf 보안을 사용하지 않는다
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //JTW를 사용할 것이기에 세션을 사용하지 않는다.
                .and()
                .authorizeRequests()
                .requestMatchers("/index").permitAll()
                .requestMatchers("/userinfo").permitAll()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/users/login").permitAll() //모든 API에 대해서 모든 요청 허가
                .requestMatchers("/users/test").hasRole("USER")
                .anyRequest().authenticated() // 이외에는 모든 요청에 대해서 인증을 필요로 한다.
                .and() // JWT 인증을 위해 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행하겠다.
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    //패스워드 Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
