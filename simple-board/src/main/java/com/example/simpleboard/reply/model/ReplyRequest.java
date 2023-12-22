package com.example.simpleboard.reply.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
//@Builder 이걸 빌더를 쓸 일이 딱히 없을것 같아서 일단 꺼보자.
//이건 dto다. request는 사용자가 요청한 데이터를 담는 객체이다.
public class ReplyRequest {

    private Long postId;

    @NotBlank
    private String title;

    @NotBlank
    private String userName;

    @NotBlank
    @Size(min = 4, max = 4)
    private String password;

    @NotBlank
    private String content;


}
