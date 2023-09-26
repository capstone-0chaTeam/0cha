package com.capstone_0CHA.service;

import com.capstone_0CHA.repository.UserRepository;
import com.capstone_0CHA.security.JwtTokenProvider;
import com.capstone_0CHA.security.TokenInfo;
import org.springframework.security.core.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService  {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;


    public TokenInfo login(String memberId , String password){
        //1.Login ID/PW 를 기반으로 Authentication 객체 생성

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

        //2. 실제 검증 사용자 비밀번호 체크

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //3.인증 정보를 기반으로 JWT 토큰 셍성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }


}
