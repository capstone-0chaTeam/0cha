package com.capstone_0CHA.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/*
* User class : DB에서 사용자 정보를 가져오거나 사용자를 등록하기 위해 사용
* User 클래스에서 UserDetails 인터페이스를 구현하여 Spring Security 에서 사용자 정보를 제공한다.
* 이 인터페이스를 통해 security 가 사용자 인식, 인증 ,권한 부여 등을 할수 이쎅 된다.
* */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {



    @Id @GeneratedValue
    @Column(updatable = false , unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING) // 문자열 형태로 열거형 저장
//    private List<Role> roles = Collections.singletonList(Role.USER);
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
