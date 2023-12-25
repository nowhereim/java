package com.example.simpleboard.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Api<T> { //이건 API의 응답을 담당하는 클래스이다. 즉, API의 응답을 담당하는 클래스이다.
    private T body;

    private Pagination pagination; // 이건 페이징을 객체.
}
