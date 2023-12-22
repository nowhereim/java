package com.example.JpaMigrationLab.user.db;

import com.example.JpaMigrationLab.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //이렇게 해야 빈 에러가 안나고있음.
public interface UserRepository extends JpaRepository<UserEntity, Long> {
//얘를 기반으로 구현체를 만들기때문에 추가적인 기능이 있어야한다면
    //여기에 정의하면 강제로 구현체를 만들꺼임.


    //쿼리 메서드에서 지정해둔 키워드를 제외하고 첫번째부터 변수가 들어가는데 jpa가 이걸 보고 쿼리를 만들어줌.
    public List<UserEntity> findAllByScoreGreaterThanEqual(int score);
    //여기서는 findAllBy랑 GreaterThanEqual이 쿼리 키워드라 제외하고 가운데 Score에 값이 들어박히는거임.


    //이것은 Score를 기준으로 키워드들이 잘라진다. 변수 2개가 차례대로 할당되고 비교연산이 시작되는것이다.
    List<UserEntity> findAllByScoreGreaterThanEqualAndScoreLessThanEqual(int score1, int score2);



    //아래와같이 쿼리를 직접 쓸수도있음. 별로 권장하지는 않으나 필요하면 이렇게 .
//    @Query("select u from user u where u.score >= ?1 and u.score <= ?2")

    @Query(
        value = "select * from user as u where u.score >= ?1 and u.score <= ?2" ,
        nativeQuery = true //이렇게하면 sql문을 쓸수있음. 위에보다 덜 가공된 쿼리를 쓸수있음.
    )
    List<UserEntity> score(int score1, int score2);
    //score(
    // @Param("score1") int score1,
    // @Param("score2") int score2
    // );
    //이렇게 이름을 지정해주고 쿼리문 안에서 ?1, ?2를 쓰는게 아니라 이름을 쓰면 된다.
}
