package com.example.simpleboard.reply.service;

import com.example.simpleboard.crud.CRUDAbstractService;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.db.ReplyRepository;
import com.example.simpleboard.reply.model.ReplyDto;
import com.example.simpleboard.reply.model.ReplyRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 서비스 빈으로 등록
public class ReplyApiService extends CRUDAbstractService<ReplyDto, ReplyEntity> {
/*    public final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public ReplyApiService(ReplyRepository replyRepository , PostRepository postRepository){
        this.replyRepository = replyRepository; //생성자 주입
        this.postRepository = postRepository;
    };

    public ReplyEntity save(ReplyRequest replyRequest){
        Optional<PostEntity> id = postRepository.findById(replyRequest.getPostId());
        //그냥 위에서 .get()으로 해버리면 null체크가 안되서 원래는 그렇게 하면안되고 아래처럼 널체크 항상 해줘야함.
        if(id.isEmpty()){
            throw new RuntimeException("게시글이 없습니다. : " + replyRequest.getPostId());
        }


        ReplyEntity entity = ReplyEntity.builder()
                .post(id.get())
                .userName(replyRequest.getUserName())
                .password(replyRequest.getPassword())
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .status("COMMENTED")
                .replyedAt(java.time.LocalDateTime.now())
                .build();

        return replyRepository.save(entity);

    };


    public List<ReplyEntity> findAllByPostId(Long postId) {
        System.out.println("여기서 뭘 찾아가기는 하나요? ================");
        System.out.println(replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId,"COMMENTED"));
        System.out.println("여기서 뭘 찾아가기는 하나요? ================");
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId,"COMMENTED");
    }*/
}
