package com.example.exception.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.http.HttpRequest;

@Data //getter, setter, toString, equals, hashCode등 자동으로 만들어줌.
@NoArgsConstructor //인자없는 생성자 만들어줌.
@AllArgsConstructor //모든 인자를 가진 생성자 만들어줌.
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) //스네이크로 갈겨버리기
public class Api <T>{
    private String resultCode;
    private String resultMessage;
    private T data;


//    public static HttpRequest.Builder builder() {
//    }
}
