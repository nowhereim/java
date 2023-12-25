package org.delivery.api.common.exception;

import lombok.Getter;
import org.delivery.api.common.error.ErrorCodeIfs;

@Getter
public class ApiException extends RuntimeException implements ApiExceptionIfs{ //런타임 익셉션을 상속받음.
    private final ErrorCodeIfs errorCodeIfs;
    private final String errorDescription;

    public ApiException(ErrorCodeIfs errorCodeIfs){
        super(errorCodeIfs.getDescription()); //에러코드의 디스크립션을 메세지로 넣어줌.
        this.errorCodeIfs = errorCodeIfs; //에러코드를 넣어줌.
        this.errorDescription = errorCodeIfs.getDescription(); //에러코드의 디스크립션을 넣어줌.
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, String errorDescription){
        super(errorDescription); //디스크립션을 직접 작성해서 넣어줌.
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, Throwable e){
        super(e);
        this.errorCodeIfs = errorCodeIfs; //에러코드를 넣어줌.
        this.errorDescription = errorCodeIfs.getDescription(); //에러코드의 디스크립션을 넣어줌.
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, Throwable e , String errorDescription){
        super(e);
        this.errorCodeIfs = errorCodeIfs; //에러코드를 넣어줌.
        this.errorDescription = errorDescription; //직접 디스크립션 넣어줌.
    }
}
