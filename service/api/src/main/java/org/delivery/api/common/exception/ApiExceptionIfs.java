package org.delivery.api.common.exception;

import org.delivery.api.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs { //ApiExceptionIfs 인터페이스를 만듦.
    ErrorCodeIfs getErrorCodeIfs(); //에러코드를 가져오는 메소드를 만듦.

    String getErrorDescription(); //에러디스크립션을 가져오는 메소드를 만듦.
}
