package com.example.simpleboard.board.db;

import com.example.simpleboard.post.db.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.util.List;

//@Getter
//@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "board")
public class BoardEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;
    private String status;

    @OneToMany(
//            fetch = FetchType.LAZY, //이건 지연로딩을 의미한다. 즉, 게시판을 조회할때 게시글을 같이 조회하지 않는다는 뜻이다.
            mappedBy = "board" // 이건 PostEntity에 있는 boardEntity를 말한다. 즉, PostEntity에 있는 boardEntity를 기준으로 매핑을 한다는 뜻이다.
//            cascade = CascadeType.ALL //이건 게시판을 삭제하면 게시판에 달린 모든 게시글을 삭제한다는 뜻이다.
//            cascade = CascadeType.REMOVE //이건 게시판을 삭제하면 게시판에 달린 모든 게시글을 삭제한다는 뜻이다.

    ) //이건 1:N관계를 말한다. 즉, 하나의 게시판에 여러개의 게시글이 달릴 수 있다는 뜻이다.
    @SQLRestriction("status = 'REGISTERED'") //이건 게시판을 조회할때 status가 ACTIVE인 것만 조회한다는 뜻이다. where는 하버네이트 6.3부터 지원 중단되어서 이거쓰면된다.
    @OrderBy("id desc") //이건 게시판을 조회할때 id를 기준으로 내림차순으로 조회한다는 뜻이다.
    private List<PostEntity> postList;
}
