package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

//
//User의 경우 1000번대 에러코드 사용
//
@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeIfs{


    USER_NOT_FOUND(404, 1001, "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(409, 1002, "이미 존재하는 사용자입니다."),
    USER_NOT_MATCH(409, 1003, "사용자 정보가 일치하지 않습니다."),


    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

}
