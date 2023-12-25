package com.example.simpleboard.post.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    // selet * from post where id = ? and status = ? order by id desc limit 1
    public Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(Long id , String status);
    //By , And를 기준으로 실행되고 Id와 Status를 입력했기때문에 이를 기준으로 찾아낸다.
    //위 코드를 보면 id와 status를 받아서 id를 기준으로 내림차순 정렬을 하고 limit 1을 걸어서 1개만 가져오는 쿼리문이다.

}
