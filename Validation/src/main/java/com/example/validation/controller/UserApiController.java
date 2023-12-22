package com.example.validation.controller;

import com.example.validation.model.Api;
import com.example.validation.model.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserApiController {

    @PostMapping("")
    public Api<UserRegisterRequest> register( //? extends Object는 모든 클래스를 받을 수 있음.
            @Valid //이게 붙으면 밑에 @NotBlank @Size @NotNull @Min @Max @Email @Pattern @FutureOrPresent 등등 다 적용됨.
            @RequestBody
            Api<UserRegisterRequest> userRegisterRequest
        ){
        log.info("{}", userRegisterRequest);

        var body = userRegisterRequest.getData();
        Api<UserRegisterRequest> response = Api.<UserRegisterRequest>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))
                .resultMessage(HttpStatus.OK.getReasonPhrase())
                .data(body)
                .build();
        return response;

    }
}

//에러로직을 여기서 처리하면 비지니스로직에 집중하지못하는 난잡한 코드가 된다. 따로 뺴고 얘는 정상화.