package com.example.simpleboard.post.controller;


import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.service.PostApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
