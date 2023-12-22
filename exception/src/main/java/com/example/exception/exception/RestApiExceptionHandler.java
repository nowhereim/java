package com.example.exception.exception;

import com.example.exception.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
//@RestControllerAdvice // RestController에서 발생하는 예외를 모두 잡아주는 핸들러임.
@RestControllerAdvice(basePackages = "com.example.exception.controller") //특정 패키지만 잡아줄 수도 있음.
@Order(3) //이것보다 낮은 값을 가진 핸들러가있으면 그게 실행될거임.
public class RestApiExceptionHandler {
    @ExceptionHandler(value = Exception.class) // Exception.class에 해당하는 예외가 발생하면 이 메소드를 실행한다.
    public ResponseEntity<Api> exception(Exception e){
        log.error("내가만든에러핸들러",e);
        var response = Api.builder()
                .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .resultMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(response);
    };

    @ExceptionHandler(value = IndexOutOfBoundsException.class) //이렇게 하면 신기한게 위에 상위 엑셉션은 이것을 잡아내지않고 여기서만 잡힘.
    public ResponseEntity outOfBound(Exception e){
        log.error("인덱스아웃오브바운드만 잡을꺼지롱",e);
        return ResponseEntity.status(200).build();
    };

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Api> noSuchElement(
            Exception e
    ){
        log.error("이건 또 노서치엘리먼트",e);
        Api<Object> response = Api.builder()
                .resultCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .resultMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();
//        return response; //반환을 리스폰스엔티티로 안하면 위에처럼 데이터자체는 404코드로 넘어가는데
        //헤더에 찍히는 스테이터스코드는 200으로 내려가버림.
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    };
}
