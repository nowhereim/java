package com.example.producingwebservice.controller;

import com.example.producingwebservice.model.BookRequest;
import com.example.producingwebservice.model.UserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController // 이 클래스는 REST API를 처리하는 컨트롤러 라고 알려주는 어노테이션
@RequestMapping("/api") // RequestMapping은 URI를 지정해주는 어노테이션
public class PostApiController {
    @PostMapping(path = "/post")
    public BookRequest post(@RequestBody BookRequest bookRequest) { //포스트 바디로 들어오는 값을 리퀘스트 바디에서 잡아서 변수 할당 해주고 있음.
        //nestjs에서는 클래스벨리데이터를 쓰는데 여기서는 이 타입에 어긋나면 자동으로 400에러를 뱉어줄거임.
        System.out.println(bookRequest);
        return bookRequest; //스프링은 개빡센게 보낼때 콘텐츠 타입이 JSon이 아니라 text면 바로 에러 박아버림.

    }

    @PostMapping(path = "/comtomecom")
    public UserRequest User(
            @RequestBody UserRequest userRequest
            ){
        System.out.println("userRequest = " + userRequest);
        log.info("요롷게찍으면 옆에 중괄호에 들어가더라고? : {}",userRequest);
        return userRequest;

    }

//    @PostMapping(path = "/pur") 여길 수정하면 자동 빌드 되는지 봐야함.
}
