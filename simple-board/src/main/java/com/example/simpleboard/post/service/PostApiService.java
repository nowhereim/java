package com.example.simpleboard.post.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.common.Api;
import com.example.simpleboard.common.Pagination;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostDeleteRequest;
import com.example.simpleboard.post.model.PostDto;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.reply.service.ReplyApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostApiService {
    public final PostRepository postRepository;
    public final ReplyApiService replyApiService; //게시글에 달린 댓글도 같이 땡겨올라고 replyApiService를 가져옴
    public final BoardRepository boardRepository;
    private final PostConverter postConverter;


    public PostEntity save(
            PostRequest postRequest
    ){
       BoardEntity boardEntity = boardRepository.findById(postRequest.getBoardId()).get(); //boardId를 받아서 boardEntity를 가져온다.


        PostEntity entity = PostEntity.builder()
                .title(postRequest.getTitle())
//                .boardId(postRequest.getBoardId()) 이건 직접적으로 boardId를 넣어주는 방식이었음.
                .board(boardEntity) //이건 boardId를 객체로 받아서 넣는 방식이다.
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .content(postRequest.getContent())
                .status("REGISTERED")
                .postedAt(new java.util.Date())
                .build();

        return postRepository.save(entity);
    };

    // 게시글 있음? 비번 맞음?
    public PostDto view(PostViewRequest postViewRequest) {
        PostEntity entity = postRepository.findFirstByIdAndStatusOrderByIdDesc(
                        postViewRequest.getPostId() , "REGISTERED")
                .map( it -> {
                    // 게시글 있음
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        String format = "패스워드가 틀렸습니다. %s !== %s"; //s는 문자열 d는 숫자
                        //format은 문자열을 만들어주는 역할
                        throw new RuntimeException( String.format(format,it.getPassword(),postViewRequest.getPassword()));
                    }
                    //결과가 있으면 댓글도 땡겨오기
//                    it.setReplyList(replyApiService.findAllByPostId(it.getId())); //리플라이서비스의 findAllByPostId를 호출해서 댓글을 땡겨온다.
                    //위 코드처럼 직접적으로 불러올 필요가 없어짐. 관계썰정이 되어서.
                    return it;
                }).orElseThrow(() -> {
                    return new RuntimeException("게시글이 없습니다.:" + postViewRequest.getPostId()
                    );
                });

        System.out.println("=============변환된 엔티티 테스트 ============== :" + entity);
        return postConverter.toDto(entity);

    }

    public Api<List<PostEntity>> findAll(
            Pageable pageable
    ) {
        Page<PostEntity> pageData = postRepository.findAll(pageable); //Page타입은 페이징처리를 위한 타입이다.
        //페이저블을 findAll에 인자로 넣으면 반환될때 페이징처리가 된다. 페이지 데이터들이 반환된다. 아래와같은 값들을 가져올수있다.
        var pagination = Pagination.builder()
                .page(pageData.getNumber())
                .size(pageData.getSize())
                .currentElements(pageData.getNumberOfElements())
                .totalElements(pageData.getTotalElements())
                .totalPage(pageData.getTotalPages())
                .build();

        //반환된 페이지 정보를 이용해서 위와같이 페이지네이션 객체로 빌드해준다.

        var response = Api.<List<PostEntity>>builder()
                .body(pageData.toList())
                .pagination(pagination)
                .build();

        //반환할때는 Api타입으로 반환해준다. Api타입은 body와 pagination을 가지고있다.
        //body에는 페이지 데이터를 리스트로 반환해주고 pagination에는 위에서 만든 pagination객체를 넣어준다.
        //원래는 쿼리 다 때려서 만들었는데 jpa를 쓰면 이렇게 개꿀따리로 만들어준다.

        return response;
    }

    public void delete(PostDeleteRequest postDeleteRequest) {
        postRepository.findById(postDeleteRequest.getPostId()).map(
                it -> {
                    if(!it.getPassword().equals(postDeleteRequest.getPassword())){
                        String 검증 = "패스워드가 틀렸습니다. %s !== %s";
                        String 반환 = String.format(검증,it.getPassword(),postDeleteRequest.getPassword()); //format은 문자열을 만들어주는 역할
                        throw new RuntimeException(반환);
                    }
                    it.setStatus("DELETED");
                    postRepository.save(it); //save는 insert와 update를 모두 수행한다.  이 경우에는 회원상태를 DELETED로 바꿔준다.
                    return it;
                }
        ).orElseThrow(()-> new RuntimeException("게시글이 없습니다.:" + postDeleteRequest.getPostId())
        );
    }
}
