package com.example.simpleboard.reply.controller;


import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.model.ReplyRequest;
import com.example.simpleboard.reply.service.ReplyApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/reply")
@RestController
@RequiredArgsConstructor//final이 붙은 필드를 인자값으로 하는 생성자를 만들어줌
public class ReplyApiController {
    private final ReplyApiService replyApiService;


    @PostMapping
    public ReplyEntity save(
            @Valid
            @RequestBody
            ReplyRequest replyRequest
    ){
        return replyApiService.save(replyRequest);
    };
}
