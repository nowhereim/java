package com.example.memorydb.user.db;

import com.example.memorydb.db.SimpleDataRepository;
import com.example.memorydb.user.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //이렇게 해야 빈 에러가 안나고있음.
public class UserRepository extends SimpleDataRepository<UserEntity, Long> {

//    심플데이터레포지토리에 직접 정의 안하고 이렇게 따로 뺄 수도 있다. 공통기능이 아니기때문에?
    public List <UserEntity> findOverScore(int score){
        return findAll().stream()
                .filter(userEntity -> userEntity.getScore() >= score)
                .collect(Collectors.toList());
    }
}
