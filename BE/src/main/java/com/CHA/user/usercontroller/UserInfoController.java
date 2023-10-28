package com.CHA.user.usercontroller;

import com.CHA.game.Entity.Stock;
import com.CHA.jwt.service.JwtService;
import com.CHA.user.dto.UserInfoDto;
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
    @GetMapping("/Id_NickName_Balance")
    public ResponseEntity<String> userInfo(HttpServletRequest request){

        System.out.println(request.getHeader("Authorization"));
        //Optional안에 uesremail이 담겨있음
        Optional<String> useremail = jwtService.extractAccessTokenToUserInfo(request);
        System.out.println(useremail);
        //userinfo User형식

        UserInfoDto userInfoDto = userInfoService.userInfo_Id_NickName_Balance(useremail);
        System.out.println(userInfoDto.getUser_id());
        System.out.println(userInfoDto.getBalance());
        System.out.println(userInfoDto.getNickname());
        System.out.println(userInfoDto.getBalance());
        return ResponseEntity.ok().body("성공"+userInfoDto);
    }

    @GetMapping("/rank")
    public ResponseEntity<String> rank(){

        List<Stock> rank = userInfoService.usersInfo_rank();

        log.info("rank : {}" ,rank);

        log.info("rank : {}" ,rank.get(0).getUser().getEmail());
        log.info("rank : {}" ,rank.get(1).getUser().getEmail());
        log.info("rank : {}" ,rank.get(2).getUser().getEmail());
        log.info("rank : {}" ,rank.get(0).getUser().getId());
        log.info("rank : {}" ,rank.get(1).getUser().getId());
        log.info("rank : {}" ,rank.get(2).getUser().getId());

        return ResponseEntity.ok().body("good" + rank);
    }

}
