package com.capstone_0CHA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicController {

    @GetMapping("/index")
    public String index() {
        return "index"; // index.html 파일을 반환
    }

    @GetMapping("/userinfo")
    public String userinfo(){
        System.out.println("이동합니다!!");
        return "userinfo";
    }
//    @GetMapping("/home")
//    public String home(){
//        System.out.println("이동합니다!!");
//        return "home";
//    }


}
