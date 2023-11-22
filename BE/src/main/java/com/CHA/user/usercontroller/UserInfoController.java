package com.CHA.user.usercontroller;

import com.CHA.game.Entity.Stock;
import com.CHA.jwt.service.JwtService;
import com.CHA.user.dto.UserInfoDto;
import com.CHA.user.dto.UserRankerDto;
import com.CHA.user.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

//user에 대한 정보를 반환하는 컨트롤러

@RestController
@Slf4j
@RequestMapping("/userInfo")
@RequiredArgsConstructor
public class UserInfoController {


    private final JwtService jwtService;
    private final UserInfoService userInfoService;

    // /user1 request를 받아서 이메일을 반환해준다.
    //@RequestHeader 어노테이션을 활용해서 리펙토링 해보기
    //@RequestHeader("Authorization") String authorizationHeader 형식으로
    //Optional안에 uesremail이 담겨있음
    //userinfo User형식
    @GetMapping("/Id_NickName_Balance")
    public ResponseEntity<UserInfoDto> userInfo(HttpServletRequest request){

        Optional<String> useremail = jwtService.extractAccessTokenToUserInfo(request);
        UserInfoDto userInfoDto = userInfoService.userInfo_Id_NickName_Balance(useremail);

        return ResponseEntity.ok().body(userInfoDto);

    }


    //RANK했을때 user가 3명 이하면 INdexOutOfBoundsExceoption 뜸
    @GetMapping("/rank")
    public ResponseEntity<List<UserRankerDto>> rank(){       // 시큐리티 설정에서 도메인 열어주기

        List<UserRankerDto> ranker = userInfoService.usersInfo_rank();
        return ResponseEntity.ok(ranker);
    }
}
