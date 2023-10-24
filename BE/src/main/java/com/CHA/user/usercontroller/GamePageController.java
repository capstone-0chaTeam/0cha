package com.CHA.user.usercontroller;


import com.CHA.jwt.service.JwtService;
import com.CHA.user.dto.UserLoginDto;
import com.CHA.user.dto.UserSignUpDto;
import com.CHA.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
@Slf4j
public class GamePageController {
    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/home")
    private String home(){
        return "/home";
    }

    @PostMapping("/basic-signup")
    public ResponseEntity<String> basicSignUp(@RequestBody UserSignUpDto userSignUpDto) throws Exception {
        log.info(userSignUpDto.getEmail());
        log.info(userSignUpDto.getNickname());
        System.out.println(userSignUpDto.getEmail());
        userService.signUp(userSignUpDto);
        return ResponseEntity.ok().body("회원가입 성공");
    }





//    @PostMapping("/user-login")
//    public ResponseEntity<String> login(HttpServletResponse response ,
//                        UserLoginDto userLoginDto){
//
//        String accessToken = jwtService.createAccessToken(userLoginDto.getEmail());
//        String refreshToken = jwtService.createRefreshToken();
//
//        jwtService.sendAccessAndRefreshToken(response,accessToken,refreshToken);
//
//        return ResponseEntity.ok().body("asd");
//    }

}
