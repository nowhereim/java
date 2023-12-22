package com.example.simpleboard.post.db;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long boardId;

    private String title;

    private String userName;

    private String password;

    private String email;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Date postedAt;


}
