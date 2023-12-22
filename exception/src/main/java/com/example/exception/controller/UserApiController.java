package com.example.exception.controller;

import com.example.exception.model.Api;
import com.example.exception.model.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController {

//    private static List<UserResponse> userList = List.of(
//            new UserResponse("1","steve",10),
//            new UserResponse("2","jane",20),
//            new UserResponse("3","marry",30)
//    );

    //빌더 패턴을 쓰면 다음 코드를 쓸 수 있다.
    private static List<UserResponse> userList = List.of(
            UserResponse.builder().id("1").name("안태환").age(10).build(),
            UserResponse.builder().id("2").name("야스오").age(20).build(),
            UserResponse.builder().id("3").name("요네").age(30).build()
    );
    
    @GetMapping("id/{userId}")
    public Api<UserResponse> getUser(
            @PathVariable String userId
    ){


        if(true)
            throw new RuntimeException("에러가 터져 버렸따리 입니다.");
        var user = userList.stream()
                .filter(it -> it.getId().equals(userId)) //id가 userId와 같은 데이터를 찾는다.
                .findFirst()//첫번째 데이터를 찾는다.
                .get();
//                .orElseThrow(() -> new RuntimeException("데이터가 없습니다.")); //데이터가 없으면 예외를 던진다.


        Api<UserResponse> response = Api.<UserResponse>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value())) //200 인트를 스트링으로 바꿔버림.
                .resultMessage(HttpStatus.OK.name()) //OK
                .data(user)
                .build();


        return response;

    }

}
