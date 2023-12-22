package com.example.simpleboard.post.service;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostApiService {
    public final PostRepository postRepository;


    public PostEntity save(
            PostRequest postRequest
    ){
        PostEntity entity = PostEntity.builder()
                .title(postRequest.getTitle())
                .boardId(postRequest.getBoardId())
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .content(postRequest.getContent())
                .status("REGISTERED")
                .postedAt(new java.util.Date())
                .build();

        return postRepository.save(entity);
    };
}
