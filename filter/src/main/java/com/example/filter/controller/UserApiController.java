package com.example.filter.controller;

import com.example.filter.Interceptor.OpenApi;
import com.example.filter.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @OpenApi //만들어 놓은 어노테이션을 여기 박아줌.
    @PostMapping
    public UserRequest register(
            @RequestBody
            UserRequest userRequest
//            HttpEntity http //HttpEntity를 사용하면 로우데이터를 확인할 수 있음.
    ) {
        //여기서 문제는 클라이언트가 값을 잘못 보내도 이미 컨트롤러에서는 오브젝트로 변환되어서 들어오기 때문에 로우데이터를 봐야
        //문제를 확인할 수 있음.
//        log.info("UserRequest : {}", userRequest);
//          log.info("UserRequest : {}", http.getBody());
        log.info("UserRequest : {}", userRequest);
    return userRequest;
    }


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}