package com.example.simpleboard.post.model;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder //빌더는 생성자를 대신해서 객체를 생성해주는 역할을 한다. ??
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDto {

    private Long id;

    //이건 boardId를 하드코딩해서 떄력박는 방식이었음.
//    private Long boardId;


    private Long boardId; //이게 있으니 빌더가 만들어지고 얘는 이 객체로 직접 때려넣게되는거임.

    private String title;

    private String userName;

    private String password;

    private String email;

    private String status;

    private String content;

    private Date postedAt;

    private List<ReplyEntity> replyList;




}
