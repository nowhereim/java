package com.example.exception.exception;

import com.example.exception.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE) //우선순위 설정 가능. 낮은 숫자일수록 우선순위가 높다.
//디폴트가 멕스벨류 즉 우선순위가 가장 낮음.
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Api> exception(
            Exception e
    ){

        System.out.println("===========================================");
        log.error("이게먼저실행될꺼야? 글로벌",e);
        var response = Api.builder()
                .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
               .resultMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()) //.getResponPhrase는 에러메세지를 가져옴.
                .build();
        return ResponseEntity
                .status(500)
                .body(response);
    };
}
