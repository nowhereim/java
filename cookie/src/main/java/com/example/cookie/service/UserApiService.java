package com.example.cookie.service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;
import com.example.cookie.model.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserApiService {
    private final UserRepository userRepository;

    public void login(
            LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ){
        String id = loginRequest.getId();
        String name = loginRequest.getName();
        String password = loginRequest.getPassword();

        Optional<UserDto> User = userRepository.findByName(name);
        if(!User.isPresent()){ //isPresent()는 null이 아닌 값이 있으면 true를 반환한다.
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }

        if(User.isPresent()){ // User가 존재한다면
            if(User.get().getPassword().equals(password)){ // 비밀번호가 일치한다면
//                httpServletResponse.addHeader("Set-Cookie", "cipal=roma"); // 쿠키 생성
                Cookie cookie = new Cookie("authorization-cookie", User.get().getId()); // 쿠키 생성
                cookie.setDomain("localhost"); // 도메인 설정 naver.com , google.com 이런식으로 설정 가능
                cookie.setPath("/"); // 경로 설정 어디서 쿠키를 사용할 것인지 설정
                cookie.setMaxAge(1); // 쿠키 만료 시간 설정 -1하면 브라우저가 닫힐 때 쿠키가 삭제된다.
                cookie.setHttpOnly(true); // 자바스크립트에서 쿠키에 접근할 수 없게 설정
                cookie.setSecure(false); // https를 쓸 때 true로 설정해야함

                httpServletResponse.addCookie(cookie); // 쿠키 추가

            } else {

               throw new RuntimeException("비밀번호가 일치하지 않습니다.");
            }
        }


    }
}
