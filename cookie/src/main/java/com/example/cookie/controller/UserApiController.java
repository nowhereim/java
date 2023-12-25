package com.example.cookie.controller;

import com.example.cookie.model.LoginRequest;
import com.example.cookie.service.UserApiService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserApiService userApiService;
    @PostMapping("/login")
    public void login(
            @RequestBody
            LoginRequest loginRequest,

            HttpServletResponse httpServletResponse,
            HttpServletRequest httpServletRequest
    ) {

        userApiService.login(loginRequest, httpServletResponse);

    }
}
