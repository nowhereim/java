package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.post.model.PostDto;
import com.example.simpleboard.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardConverter {
    private final PostConverter postConverter;
    public BoardDto toDto(BoardEntity boardEntity) {

       List<PostDto> postList =  boardEntity.getPostList()
               .stream()
               .map(postConverter::toDto).toList();

        return BoardDto.builder() //이렇게 Dto빌더로 Entity를 Dto로 변환해서 반환해준다.
                .id(boardEntity.getId())
                .boardName(boardEntity.getBoardName())
                .status(boardEntity.getStatus())
                .postList(postList)
                .build();
    }
}
