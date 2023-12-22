package com.example.simpleboard.board.controller;


import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.model.BoardRequest;
import com.example.simpleboard.board.service.BoardApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardApiService boardApiService;

//    public BoardApiController(BoardApiService bookService){
//            this.boardApiService = bookService;
//    };

    @GetMapping
    public List<BoardEntity> findAll(){
        return boardApiService.findAll();
    };

    @PostMapping()
    public BoardEntity save(
            @Valid
            @RequestBody
            BoardRequest boardEntity
    ){
        return boardApiService.save(boardEntity);
    };
}
