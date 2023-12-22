package com.example.JpaMigrationLab.user.contlroller;

import com.example.JpaMigrationLab.user.model.UserEntity;
import com.example.JpaMigrationLab.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Controller //이건 mvc에서 주로 씀. html파일을 반환할때
@RestController //이건 http응답을 보통 반환할때씀.
@RequestMapping("/api/user")
@RequiredArgsConstructor //final이 붙은 필드를 인자로 받는 생성자를 만들어줌.
public class UserApiController {

    private final UserService userService;

    @PutMapping //생성도하고 업뎃도하니까 걍 풋
    public UserEntity create(
            @RequestBody UserEntity userEntity //원레 엔티티들 여기서 받지않는다고함.
    ){

        return userService.save(userEntity);
    };

    @GetMapping("/all")
    public List<UserEntity> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> findById(
            @PathVariable Long id
    ){
        return userService.findById(id);
    }

    @GetMapping("/over/{score}")
    public List<UserEntity> findOverScore(
            @PathVariable int score
    ){
        return userService.findOverScore(score);
    };

    @DeleteMapping("/{id}")
    public void deleteById(
            @PathVariable Long id
    ){
        userService.deleteById(id);
    }

    @GetMapping("/between/{score1}/{score2}")
    public List<UserEntity> findBetweenScore(
            @PathVariable int score1,
            @PathVariable int score2
    ) {
        return userService.findBetweenScore(score1, score2);
    }


}
//215,000 maybeUnder?