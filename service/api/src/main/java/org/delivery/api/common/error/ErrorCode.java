package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodeIfs {

    OK(200,200,"성공"),
     BAD_REQUEST(HttpStatus.BAD_REQUEST.value(),400,"잘못된 요청입니다."),

    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(),500,"서버 에러입니다."),

    NULL_POINTER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(),512,"널포인터 에러입니다."),


    ;





    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

/*이렇게 하드코등 안하고 Getter 어노테이션 달면 위 변수만들어둔것때문에 자동으로 겟메서드생김.
    @Override
    public Integer getHttpStatus() {
        return this.httpStatusCode;
    }

    @Override
    public Integer getCode() {
        return this.errorCode
    }

    @Override
    public String getDescription() {
        return this.description;
    }*/
}
