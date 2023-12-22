package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardApiService {
    public final BoardRepository boardRepository;

    public List<BoardEntity> findAll(){
        return boardRepository.findAll();
    };

    public BoardEntity save(BoardRequest boardEntity){
        //요청할때 넘어온 객체를 받았음. 여기서는 boardName 만 받음.
        System.out.println(boardEntity);
        var entity = BoardEntity.builder()
                .boardName(boardEntity.getBoardName())
                .status("REGISTERED")
                .build();
        //리퀘스트 객체를 가지고 엔티티 객체를 만들어 jpa에 저장하기위해
        //빌더를 사용해서 엔티티 객체를 만들고 이걸 save 메소드에 넘겨줌.
        System.out.println(entity);
        //이부분이 모호해서 더 익숙해져야할듯.

        return boardRepository.save(entity);

    };
}
