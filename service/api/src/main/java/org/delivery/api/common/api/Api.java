package org.delivery.api.common.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCodeIfs;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {


    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> OK(T data){
        var api = new Api<T>();
        api.result = Result.OK();
        api.body = data;
        return api;
    }


    //에러를 받아서 바디로 내려줄게 없음. 그냥 에러만 내려줄게. 그래서 제네릭경고를 줄 필요가 없어서 그냥 오브젝트 타입으로 해도 됨.
    public static <T> Api<Object> ERROR(Result result){
        var api = new Api();
        api.result = result;
        return api;
    }

    public static <T> Api<Object> ERROR(ErrorCodeIfs errorCodeIfs){
        var api = new Api();
        api.result = Result.ERROR(errorCodeIfs);
        return api;
    }

    public static <T> Api<Object> ERROR(ErrorCodeIfs errorCodeIfs,Throwable e){
        var api = new Api();
        api.result = Result.ERROR(errorCodeIfs , e);
        return api;
    }

    public static <T> Api<Object> ERROR(ErrorCodeIfs errorCodeIfs,String description){
        var api = new Api();
        api.result = Result.ERROR(errorCodeIfs ,description);
        return api;
    }
}
