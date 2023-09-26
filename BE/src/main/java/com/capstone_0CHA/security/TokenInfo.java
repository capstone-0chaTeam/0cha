package com.capstone_0CHA.security;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
@Data
@AllArgsConstructor
public class TokenInfo {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
