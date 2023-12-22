package com.example.producingwebservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data //이거는 게터 세터 투스트링 이퀄 해쉬코드 다 만들어줌.
@AllArgsConstructor //이거는 모든 필드를 파라미터로 받는 생성자를 만들어줌.
@NoArgsConstructor //이거는 파라미터가 없는 생성자를 만들어줌.
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) //스네이크 케이스로 들어와도 자동으로 바꿔줌 스프링 개사기네 js보다 더 심하네
public class UserRequest {
    private String userName;
    private int userAge;
    private String email;
//    private boolean isKorean; //이거 Korean아니면 무조건 false로 들어옴. 쓰레기임. 그래서 아래처럼 해야함.
    private Boolean isKorean; //이렇게 하면 null도 들어올 수 있음. 그래서 이걸로 해야함. 객체형으로 해야함. 일반자료형이 아니라.
    public String get여기서부터이름으로쓸거지롱() { //이렇게하면 오브젝트매퍼는 get다음것을 이름으로 해서 직렬화시킴
        return userName;
    }
    public int getUserAge() {
        return userAge;
    }

    public String getEmail() {
        return email;
    }


    public Boolean getIsKorean() {
        return isKorean;
    }
//    @JsonIgnore //이걸 붙이면 json으로 변환할때 이 변수는 무시하고 변환함. json으로 변환할때 라는 것은 직렬화할때임.
    @JsonProperty("변환되었지용") //이렇게 하면 json으로 변환할때 이 변수를 이 이름으로 변환함. 직렬화할때임.
    public Boolean get나도직렬화할거냐(){
        return true;
    };
}
