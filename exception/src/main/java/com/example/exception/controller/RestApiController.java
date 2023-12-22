package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping(path="/hello")
    public void hello() {
        var list = List.of("hello");
        var val = list.get(1);
        log.info("val: {}", val); //java.lang.IndexOutOfBoundsException: Index: 1 Size
        //에러터짐. 없는 인덱스를 찾으려고 해서.


//        throw new RuntimeException("에러가 터져 버렸따리 입니다.");
    }
}
