package com.example.simpleboard.post.service;


import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostDto;
import org.springframework.stereotype.Service;

@Service
public class PostConverter {
    public PostDto toDto(PostEntity postEntity) {
        return PostDto.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .email(postEntity.getEmail())
                .content(postEntity.getContent())
                .boardId(postEntity.getBoard().getId())
                .status(postEntity.getStatus())
                .postedAt(postEntity.getPostedAt())
                .password(postEntity.getPassword())
                .userName(postEntity.getUserName())
                .replyList(postEntity.getReplyList())
                .build();
    }
}
//다른곳에서 postDto를 사용해야할때에도 이걸 반환해줌.
//dto는 엔티티를 직접적으로 내리게되었을때 발생할 수 있는 불필요한 정보 전송으로인한 보안 문제를 해결한다.
//여기서 필요한 데이터로만 가공해서 내려주기 위해 쓴다.