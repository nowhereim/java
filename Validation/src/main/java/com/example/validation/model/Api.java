package com.example.validation.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class) //스네이크 케이스로 받고 보냄. 직렬화가 일어남.
public class Api<제네릭> {

        private String resultCode;
        private String resultMessage;
        @Valid //이렇게해야 데이터로 들어오는 녀석을 검증함.
        private 제네릭 data;
        private Error error;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class) //스네이크 케이스로 받고 보냄. 직렬화가 일어남.
        public static class Error{
        private List<String> errorMessages;
    }
}
