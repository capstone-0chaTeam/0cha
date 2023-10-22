package com.CHA.user.usercontroller;

import com.CHA.jwt.service.JwtService;
import com.CHA.user.dto.UserSignUpDto;
import com.CHA.user.repository.UserRepository;
import com.CHA.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserSignUpDto userSignUpDto) throws Exception {
        userService.signUp(userSignUpDto);
        return "회원가입 성공";
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }

//    @GetMapping("/user-info")
//    public ResponseEntity<String> getUserInfo(@RequestHeader("Authorization") String accessToken){
//
//        Optional<String> nickname = jwtService.extractNickname(accessToken);
//        log.info("nickname : {}" ,nickname);
//        log.info("accessToken : {}" ,accessToken);
//
//        if (nickname.isPresent()){
//            return ResponseEntity.ok(nickname.get());
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("엑세스 토큰이 유효하지 않습니다.");
//        }
//    }

    // /user1 request를 받아서 이메일을 반환해준다.
    //@RequestHeader 어노테이션을 활용해서 리펙토링 해보기
    @GetMapping("/user1")
    public ResponseEntity<String> userEmail1(HttpServletRequest request){

        Optional<String> useremail = jwtService.extractAccessTokenToUserInfo(request);
        System.out.println(jwtService.extractAccessTokenToUserInfo(request));

        String result = useremail.map(Object::toString).orElse(null);

        if (result != null) {
            // 값을 가져왔을 때의 처리
            System.out.println(result); // 출력: "123"
        } else {
            // 값이 없을 때의 처리
            System.out.println("값이 없습니다.");
        }

        return ResponseEntity.ok().body("성공"+useremail);
    }
}
