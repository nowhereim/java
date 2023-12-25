package com.example.cookie.controller;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestApiController {
    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserDto me(
            HttpServletRequest httpServletRequest,

            @CookieValue(required = false) //name따로 지정 안해주면 변수명으로 쿠키값을 받아옴.
            String happy //이렇게 어노테이션으로 쿠키값을 받아올 수 도있음.
    ){
       log.info("happy = {}", happy);
//        Cookie[] cookies = httpServletRequest.getCookies(); //쿠키들 다 가져옴.
        return userRepository.findById(happy).orElseThrow(() -> new RuntimeException("없어요"));

//        if(cookies != null){
//            for(Cookie cookie : cookies){
//                log.info("cookie name : {}, cookie value : {}", cookie.getName(), cookie.getValue());
//            }
//        }
    }

    @GetMapping("/me2")
    public UserDto me2(
            @RequestHeader(name = "happy",required = false) //name따로 지정 안해주면 변수명으로 쿠키값을 받아옴. 이건 헤더.
            String happy //이렇게 어노테이션으로 쿠키값을 받아올 수 도있음.
    ){
        log.info("happy = {}", happy);
//        Cookie[] cookies = httpServletRequest.getCookies(); //쿠키들 다 가져옴.
        return userRepository.findById(happy).orElseThrow(() -> new RuntimeException("없어요"));

//        if(cookies != null){
//            for(Cookie cookie : cookies){
//                log.info("cookie name : {}, cookie value : {}", cookie.getName(), cookie.getValue());
//            }
//        }
    }
}
