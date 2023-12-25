package com.example.simpleboard.reply.model;

import com.example.simpleboard.post.db.PostEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//dto는 entity와 1:1 맵핑인데 필요한것만 가져오면 된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) //이것때문에 박살나는 경우가 많음.
public class ReplyDto {

    private Long id;

    private Long postId; //엔티티를 받으면 안대지 멍청아.

    private String title;

    private String userName;

    private String password;

    private String content;

    private LocalDateTime replyedAt;

    private String status;


}
//엔티티와 dto로 서로 변환이 잘 되어야함.

//컨버터를 이용해줘서.
