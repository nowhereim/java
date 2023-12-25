package com.example.session.db;

import com.example.session.model.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepository {

    private List<UserDto> userList = new ArrayList<>();

    public UserDto findByName(String name){
        return userList.stream().filter(userDto -> userDto.getName().equals(name)).findFirst()
                .orElseThrow(()-> new IllegalArgumentException("해당 유저가 없습니다."));
    } //orElse(null) : null이면 null을 반환하고 아니면 값을 반환한다.

    @PostConstruct //빈이 초기화 되었을때 호출되는 메소드
    public void init(){
        userList.add(UserDto.builder().name("홍길동").password("1234").build());
        userList.add(UserDto.builder().name("김철수").password("4321").build());
        userList.add(UserDto.builder().name("김영희").password("1212").build());
    }
}
