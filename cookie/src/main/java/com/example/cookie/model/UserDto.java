package com.example.cookie.model;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDto {

    private String id;
    private String name;
    private String password;
}
