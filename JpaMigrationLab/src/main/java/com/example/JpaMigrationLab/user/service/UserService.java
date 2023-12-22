package com.example.JpaMigrationLab.user.service;

import com.example.JpaMigrationLab.user.db.UserRepository;
import com.example.JpaMigrationLab.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //이건 비즈니스 로직을 처리할때 씀.
@RequiredArgsConstructor
public class UserService {

//    @Autowired //이거 주석해도 실행 잘 됨. 레포지토리에 서비스 어노테이션 달았더니.
    private final UserRepository userRepository;
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);

    }

 public List<UserEntity> findAll(){
        return userRepository.findAll();
 };

    public Optional<UserEntity> findById(Long id){
        return userRepository.findById(id);
    };

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public List<UserEntity> findOverScore(int score){
//        return userRepository.findOverScore(score);
        //스코어가 20보다 큰 유저들을 반환해줌.
        return userRepository.findAllByScoreGreaterThanEqual(score);



    };

    public List<UserEntity> findBetweenScore(int score1, int score2){
        return userRepository.score(score1, score2);
    }
}
