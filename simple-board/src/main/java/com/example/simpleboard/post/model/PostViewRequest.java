package com.example.simpleboard.post.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

//@Data //getter, setter, toString, equals, hashCode를 자동으로 만들어준다. 이거 안쓰고 다음과같이해도된다.
@Getter
@Setter
@ToString
@NoArgsConstructor//인자가 없는 생성자를 만들어준다.
@AllArgsConstructor//인자가 있는 생성자를 만들어준다.
@Builder //빌더는 생성자를 대신해서 객체를 생성해주는 역할을 한다. ??
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) //스네이크 케이스로 만들어준다.
public class PostViewRequest {

    @NotNull
    private Long postId;


    private String password;

}
