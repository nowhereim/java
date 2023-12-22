package com.example.jpadata.user.controller;

import com.example.jpadata.user.db.UserEntity;
import com.example.jpadata.user.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {


    private final UserRepository userRepository;
    @GetMapping("/all")
    public List<UserEntity> findAll(){

        return userRepository.findAll(); //우와 졸라 편하다..??;;;

    };
}
