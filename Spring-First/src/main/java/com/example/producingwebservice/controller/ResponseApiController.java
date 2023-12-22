package com.example.producingwebservice.controller;

import com.example.producingwebservice.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
//레스트컨트롤러랑 리퀘스트맵핑은 디폴트로 박아넣어라 멍청아.
@RestController // 이 클래스는 REST API를 처리하는 컨트롤러 라고 알려주는 어노테이션 JSON으로만 내려간다.
//@Controller //JSON만 내리는게 아니라 html같은걸 내릴떄는 이걸 써야함.
@RequestMapping("/api/v1")
//@ResponseBody //컨트롤러 어노테이션을 박으면 JSON말고 다른것도 내릴수있는데 이렇게 어노테이션으로 JSON 내릴거라고 명시 가능함.
public class ResponseApiController {
    @GetMapping("/user") //이걸 쓰면 /api/v1/user로 맵핑이 됨.
    //이걸 쓰면 메서드 강제 가능.
//    @RequestMapping(path = "/user" , method = RequestMethod.GET) 메소드르 빼먹으면 모든 메소드가 들어옴.
    public ResponseEntity<UserRequest> user(){ //리스폰스엔티티 타입으로 리턴하면 상태코드를 지정할 수 있음.

        UserRequest user = new UserRequest();
//        user.setUserName("봉알");
//        user.setUserAge(10);
//        user.setEmail("gmail");
//        user.setIsKorean(true);
        System.out.println(user); //스네이크케이스가 여기서 적용되진않음.
        log.info("user : {}",user); //로그백을 쓰면 여기서 적용됨.
        var response = ResponseEntity //리스폰스엔티티를 쓰면 상태코드 등 커스텀이 가능함.
                .status(HttpStatus.CREATED)
                .header("x-header", "lookingat")
                .body(user);
        return response; //이걸 리턴하면 자동으로 json으로 바뀌는데 그때 스네이크케이스 적용됨.
    };
}
