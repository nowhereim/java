package com.example.producingwebservice.controller;

import com.example.producingwebservice.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //이게있었네요 로그찍으려면 이거 박으세요.
@RestController
@RequestMapping("/api")

public class PutApiController {
    @PutMapping("/put") //포스트랑 풋의 차이는 이거 맵핌 어노테이션만 다름. 그 외에는 포스트는 자원 생성 풋은 자원 자체를 수정한다느건 그대로임.
    public String put(
            @RequestBody //직렬화와 역직렬화를 자동으로 해줌.
            UserRequest userRequest //json을 자바 오브젝트로 바꿔줌. 이게 바로 역직렬화.
    ){
        System.out.println(userRequest); //콘솔에 똑같이 찍지만 이걸 많이 찍을 수록 먼저 이게 찍히고 그다음에 로그가 찍히는걸 볼 수 있음. 그래서 실행 속도 저하가 올 수 있음.
        log.info("리퀘스트 : {}",userRequest); //로그를 찍어보면 리퀘스트가 잘 들어오는걸 확인할 수 있음.
        //log백을 쓰면 자체적인 버퍼를     가지고 있어서 한번에 찍어줌. 그래서 속도가 빠름. 더 좋은 점은 json으로 바꾸던 커스텀이 가능함. 그게 강점임.
        return "감사합니다.";
        //return userRequest 이렇게 하면 json으로 바뀌어서 리턴됨. 이게 직렬화.
        //즉 직렬화는 자바오브젝트를 다른 형태로 바꾸는거고 역직렬화는 다른 형태를 자바 오브젝트로 바꾸는거임.

    }
}
