package com.example.simpleboard.reply.service;

import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.db.ReplyRepository;
import com.example.simpleboard.reply.model.ReplyRequest;
import org.springframework.stereotype.Service;

@Service // 서비스 빈으로 등록
public class ReplyApiService {
    public final ReplyRepository replyRepository;

    public ReplyApiService(ReplyRepository replyRepository){
        this.replyRepository = replyRepository; //생성자 주입
    };

    public ReplyEntity save(ReplyRequest replyRequest){
        ReplyEntity entity = ReplyEntity.builder()
                .postId(replyRequest.getPostId())
                .userName(replyRequest.getUserName())
                .password(replyRequest.getPassword())
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .status("REGISTERED")
                .replyedAt(java.time.LocalDateTime.now())
                .build();

        return replyRepository.save(entity);

    };
}
