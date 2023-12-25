package com.example.cookie.model;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginRequest {

    private String id;
    private String name;
    private String password;
}
