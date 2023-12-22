package com.example.validation.exception;

import com.example.validation.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class , HttpMessageNotReadableException.class}) //배열이라 [] 이렇게 할줄 알았는데 {} 이렇게 해야함.
    public ResponseEntity<Api> validationException(
            MethodArgumentNotValidException e
//            HttpMessageNotReadableException 에러2 //이건 타임스탬프 에러인데 어케해야할지 아직 모르겠음.
    ){
        log.error("error : {}",e);
        var errorMessageList = e.getFieldErrors().stream()
                .map(it -> {
                    var format = "%s : { %s } 은 %s";
                    var message = String.format(format, it.getField(),it.getRejectedValue(),it.getDefaultMessage());
                    return message;

                }).collect(Collectors.toList());

        var error = Api.Error
                .builder()
                .errorMessages(errorMessageList)
                .build();

        var errorResponse = Api
                .builder()
                .resultCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .resultMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .error(error)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
        //이게 항상 리스폰스엔티티를 쓰지 않으면 무조건 200으로 가버림.

    }
}
