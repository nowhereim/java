package com.example.simpleboard.reply.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {
    // select * from reply where post_id = ? order by id desc
    //전부 선택 리플라이 테이블에서 post_id가 ? 인것과 status가 ? 인것을 order by id desc로 정렬해서.
    public List<ReplyEntity> findAllByPostIdAndStatusOrderByIdDesc(Long postId,String status);
}
