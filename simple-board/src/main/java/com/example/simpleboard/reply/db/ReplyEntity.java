package com.example.simpleboard.reply.db;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "reply")
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    private String title;

    private String userName;

    private String password;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime replyedAt;

    private String status;
}
