package com.example.exception.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //빌더 패턴을 사용할 수 있게 해줌.
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {
    private String id;
    private String name;
    private Integer age;
}
