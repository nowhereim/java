package com.example.simpleboard.reply.controller;


import com.example.simpleboard.crud.CRUDAbstractController;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.model.ReplyDto;
import com.example.simpleboard.reply.model.ReplyRequest;
import com.example.simpleboard.reply.service.ReplyApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/reply")
@RestController
@RequiredArgsConstructor//final이 붙은 필드를 인자값으로 하는 생성자를 만들어줌
public class ReplyApiController extends CRUDAbstractController<ReplyDto,ReplyEntity> {
/*    private final ReplyApiService replyApiService;


    @PostMapping
    public ReplyEntity save(
            @Valid
            @RequestBody
            ReplyRequest replyRequest
    ){
        return replyApiService.save(replyRequest);
    };

    @GetMapping
    public List<ReplyEntity> findAllByPostId(Long postId){
        return replyApiService.findAllByPostId(postId);
    }*/
}
