package com.example.simpleboard.board.controller;


import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.board.model.BoardRequest;
import com.example.simpleboard.board.service.BoardApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@Slf4j
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
    public BoardDto save(
            @Valid
            @RequestBody
            BoardRequest boardEntity
    ){
        return boardApiService.save(boardEntity);
    };


    @GetMapping(path = "{id}")
    public BoardEntity findById(
            @PathVariable
            Long id
    ){
        return boardApiService.findById(id);
    }

    @GetMapping(path = "/view/{id}")
    public BoardDto view(
            @PathVariable
            Long id
    ){
        var entity = boardApiService.view(id);
        log.info("entity = {}",entity);
        return boardApiService.view(id);
    }
}
//Entity가 뷰 까지 내려와서는 안된다.
//Dto가 내려오도록 해라.
//그러기 위해서는 Entity를 Dto로 Convert 해주는 작업을 생각해라. -> Converter사용.