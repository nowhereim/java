package com.example.session.controller;

import com.example.session.model.LoginRequest;
import com.example.session.service.AccountApiService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {

    private final AccountApiService accountApiService;
    @PostMapping("/login")
    public void login(
            @RequestBody
            LoginRequest loginRequest ,

            HttpSession httpSession //세션을 사용하기 위해 HttpSession을 파라미터로 받는다. 자동으로 세션 받는다고 함.

    ){
        accountApiService.login(loginRequest, httpSession);

    }
}
