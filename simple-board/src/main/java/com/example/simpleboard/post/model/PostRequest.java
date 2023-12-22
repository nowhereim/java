package com.example.simpleboard.post.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder //빌더는 생성자를 대신해서 객체를 생성해주는 역할을 한다. ??
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

//    @NotNull
    private Long boardId;

    @NotBlank
    private String userName;

    @NotBlank
    @Size(min = 4, max = 4)
    private String password;

    @NotBlank
    private String email;

}
