package com.example.simpleboard.post.db;


import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //이건 boardId를 하드코딩해서 떄력박는 방식이었음.
//    private Long boardId;


    //이건 boardId를 객체로 받아서 넣는 방식이다.
    @ManyToOne //이렇게하면 컬럼으로 인식하고 자동으로 _id를 붙여 찾는다.
    @JsonIgnore //이건 json으로 변환할때 이걸 무시하라는 뜻이다. 즉, 이걸 안하면 무한루프에 빠지게 된다. 예를들어 보드 엔티티타입으로 인해 오브젝트맵퍼가 보드엔티티를 json으로 변환하려고해서 들어가보니 보드엔티티 안에는 또다시 리스트형식으로 포스트엔티티가 있고 포스트엔티티 안에는 또다시 보드엔티티가 있고 이렇게 무한루프에 빠지게 된다.
    //이그노어 안하면 여기 조회 떄릴떄 상위 객체도 조회될듯함.
    @ToString.Exclude
    private BoardEntity board; //이게 있으니 빌더가 만들어지고 얘는 이 객체로 직접 때려넣게되는거임.

    private String title;

    private String userName;

    private String password;

    private String email;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Date postedAt;

//    @Transient //이건 테이블에 매핑하지 않는다는 뜻이다. 즉, 이건 디비에 저장하지 않는다는 뜻이다.
    //이건 디비에 때려박아둘게 아니고 그냥 DAO에서 쓸거니까.
    @OneToMany(
            mappedBy = "post"
    ) //게시글 1개에 N개의 댓글


    @Builder.Default
    //빌더패턴을 사용할떄 만약 빌드를 하지않아도 디폴트로 빈 리스트를 나오게 하려면 여기서 디폴트값을 설정하고 빌더 디폴트 하면 된다.
    private List<ReplyEntity> replyList = Arrays.asList();
    //List.of()는 불변 리스트를 만들어준다. 이렇게 디폴트를 넣어야 널이아니라 빈 리스트가 된다.
}
