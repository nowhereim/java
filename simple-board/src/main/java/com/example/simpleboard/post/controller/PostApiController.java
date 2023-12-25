package com.example.simpleboard.post.controller;


import com.example.simpleboard.common.Api;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostDeleteRequest;
import com.example.simpleboard.post.model.PostDto;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.post.service.PostApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {
    private final PostApiService postApiService;

    @PostMapping
    public PostEntity save(
            @Valid
            @RequestBody
            PostRequest postRequest

    ){
        return postApiService.save(postRequest);
    }


    @PostMapping(path = "/view")
    public PostDto view(
            @Valid
            @RequestBody
            PostViewRequest postViewRequest
    ){
        return postApiService.view(postViewRequest);
    };

    @GetMapping("/all")
    public Api<List<PostEntity>> list(
            @PageableDefault(page = 0, size = 10 , sort = "id", direction = Sort.Direction.DESC) //page는 0부터 시작, size는 10개씩 가져온다. 디폴트로 설정해놓은거임.
                    //쿼리 파라미터로 page=0&size=10 이런식으로 넣어주면 됨.
            Pageable pageable
    ) {
        return postApiService.findAll(pageable);
    };

    @DeleteMapping("/delete") //DeleteMapping은 Delete 요청을 받을 수 있는 어노테이션
    public void delete(
            @RequestBody //delete 메서드는 restAPI관점에서 보면 body에 데이터를 넣어서 보내는게 아니라 url에 데이터를 넣어서 보내는게 맞음.
            PostDeleteRequest postDeleteRequest
    ) {
        postApiService.delete(postDeleteRequest);
    };
}
