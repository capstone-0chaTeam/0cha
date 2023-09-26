package com.capstone_0CHA.api;

import com.capstone_0CHA.dto.UserDto;
import com.capstone_0CHA.security.TokenInfo;
import com.capstone_0CHA.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class Usercontroller {
    private final UserService userService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody UserDto.Request dto){
        String username = dto.getUsername();
        String password = dto.getPassword();
        TokenInfo tokenInfo = userService.login(username ,password);
        return tokenInfo;
    }

    @PostMapping("/test")
    public String test(){
        return "success";
    }


}
