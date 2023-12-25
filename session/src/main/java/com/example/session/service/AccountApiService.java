package com.example.session.service;

import com.example.session.db.UserRepository;
import com.example.session.model.LoginRequest;
import com.example.session.model.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountApiService {

    private final UserRepository userRepository;

    public void login(
            LoginRequest loginRequest,
            HttpSession httpSession
    ){
        String name = loginRequest.getName();
        String password = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(name);

        var userDto = UserDto.builder()
                .name(optionalUser.getName())
                .password(optionalUser.getPassword())
                .build();


        if(!loginRequest.getPassword().equals(userDto.getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }


        httpSession.setAttribute(loginRequest.getName(), userDto); //세션에 user라는 이름으로 userDto를 저장한다.

        System.out.println("httpSession 정보 = " + httpSession.getAttribute(loginRequest.getName()));
        System.out.println("httpSession 정보 = " + httpSession.getId());
        //세션정보 가져올때는 키값으로 조회떄리면 되는거임. 겟어트리뷰트. 키값으로 조회하면 세션에 저장된 정보를 가져올 수 있음.

    }
}
