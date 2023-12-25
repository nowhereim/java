package com.example.simpleboard.reply.service;

import com.example.simpleboard.crud.Converter;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyConverter implements Converter<ReplyDto , ReplyEntity> {

    private final PostRepository postRepository;
    @Override
    public ReplyDto toDto(ReplyEntity replyEntity) {
        ReplyDto dto = ReplyDto.builder()
                .id(replyEntity.getId())
                .postId(replyEntity.getPost().getId())
                .status(replyEntity.getStatus())
                .title(replyEntity.getTitle())
                .content(replyEntity.getContent())
                .userName(replyEntity.getUserName())
                .password(replyEntity.getPassword())
                .replyedAt(replyEntity.getReplyedAt())
                .build();

        return dto;
    }

    @Override
    public ReplyEntity toEntity(ReplyDto replyDto) {


        Optional<PostEntity> postEntity = postRepository.findById(replyDto.getPostId());

        return ReplyEntity.builder()
                .id(replyDto.getId()) // << null 이면 새로 생성하고 아니면 수정한다. save update
                .post(postEntity.orElseGet(null)) //옵셔널은 이게 없을때 어쩔래를 잘 처리해줘야함.
                .status((replyDto.getStatus() != null) ? replyDto.getStatus() : "REGISTERED")
                .title(replyDto.getTitle())
                .content(replyDto.getContent())
                .password(replyDto.getPassword())
                .userName(replyDto.getUserName())
                .replyedAt((replyDto.getReplyedAt() != null) ? replyDto.getReplyedAt() : java.time.LocalDateTime.now())
                .build();
    }
}
