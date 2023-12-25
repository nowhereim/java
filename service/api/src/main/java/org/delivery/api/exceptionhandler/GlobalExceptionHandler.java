package org.delivery.api.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice //모든 컨트롤러가 실행될 때 이 어노테이션이 붙은 클래스가 실행됨.
@Order(value = Integer.MAX_VALUE) // 제일 최 후 순위 다른거 다 건너 건너 마지막까지 안잡히는 듣보 에러는 여기서 처리해야함.
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class) //Exception이 발생하면 이 메소드가 실행됨.
    public ResponseEntity<Api<Object>> exception(
            Exception exception
    ){
        log.error("", exception); //이건 너무 자세하게 클라이언트로 넘겨주니까 우리만 보는걸로.

        return ResponseEntity
                .status(500)
                .body(Api.ERROR(ErrorCode.SERVER_ERROR));

    }
}
