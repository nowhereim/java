package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/b")
public class RestApiBController {
    @GetMapping(path="/hello")
    public void hello() {
        throw new NumberFormatException("숫자 포맷이 잘못되었습니다.");
    }

//    @ExceptionHandler(value = NumberFormatException.class)
//    public ResponseEntity numberFormatException(
//            NumberFormatException e
//    ){
//        log.error("글로벌한 레스트컨트롤러 어드바이스로 안가고 이 컨트롤러 단에서 즉시 처리 가능.",e);
//        return ResponseEntity.status(200).build();
//    }
}
