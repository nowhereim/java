package com.example.producingwebservice.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //이렇게하면 생성자에 자동으로 정의된 변수들이 들어감. ㅅㅂ 개꿀
@NoArgsConstructor //이건 파라미터 없는 기본 생성자를 만들어줌. 개꿀 ㅅㅂ
public class BookQueryParam {
    private String name;
    private Integer sign; //이거 int로 하면 null이 안들어감 그래서 처리가 개같아짐. 더 좋은 방법이 있는지는 아직 모르겠음.
    private String author;
    private Integer page;

}
//롬복이 개사기인게 게터 세터 투스트링 다 만들어줌. 이거 시발 개꿀이네