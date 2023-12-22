package com.example.producingwebservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //이렇게하면 생성자에 자동으로 정의된 변수들이 들어감. ㅅㅂ 개꿀
@NoArgsConstructor//이건 파라미터 없는 기본 생성자를 만들어줌. 개꿀 ㅅㅂ
public class BookRequest {
    private String name;
    private String number;
    private String category;
}
