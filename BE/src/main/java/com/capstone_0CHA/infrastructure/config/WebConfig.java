package com.capstone_0CHA.infrastructure.config;//package com.jwt_study.infrastructure.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
////@LoginUser : 빈 어노테이션
////LoginUserArgumentResolver : 컨트롤러 메서드의 파라미터에 사용자 정보를 주입하는 역할
//
//
////WebConfig 클래스를 사용해서 MVC에서 사용자 정보를 컨트롤러 메서드로 주입할수 있게 설정한다.
////결국 @LoginUser어노테이션을 사용할 떄 사용자 정보를 주입받고 사용할수 있게 설정해주는 역할을 한다 -> WebConfig
////WebMvcConfigurer을 구현해서 Spring MVC구성을 정의한다
//
//@RequiredArgsConstructor
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    private final LoginUserArgumentResolver loginUserArgumentResolver;
//
//    //List<HandlerMethodArgumentResolver> 에 loginUserArgumentResolver을 등록시킨다.
//    @Override
//    public void addArgumentResolver(List<HandlerMethodArgumentResolver> resolvers){
//        resolvers.add(loginUserArgumentResolver);
//    }
//}
