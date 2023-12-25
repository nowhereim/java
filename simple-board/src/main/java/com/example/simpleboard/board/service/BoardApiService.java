package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardApiService {
    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;
    public List<BoardEntity> findAll(){
        return boardRepository.findAll();
    };

    public BoardDto save(BoardRequest boardEntity){
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

        BoardEntity savedEntity = boardRepository.save(entity);
        return boardConverter.toDto(savedEntity);

    };

    public BoardEntity
    findById(Long id){
        return boardRepository.findById(id).orElseThrow();
    };

    public BoardDto view(Long id) {
        BoardEntity entity = boardRepository.findById(id).orElseThrow();
        return boardConverter.toDto(entity);
    }
}
