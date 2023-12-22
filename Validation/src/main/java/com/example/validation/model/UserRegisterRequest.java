package com.example.validation.model;

import com.example.validation.annotation.PhoneNumber;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class) //스네이크 케이스로 받고 보냄. 직렬화가 일어남.
public class UserRegisterRequest {
//    @NotNull //!= null 널만
//    @NotEmpty //!= "" && != null 널과 빈블랭크
//    @NotBlank //!= " " && != "" && != null 널 빈블랭크 공백블랭크
    private String name;

    private String nickName;
    //name과 nickName 둘중 하나만 있어도 오케이 하게 하려면?

    @Size(min = 1, max = 12 , message = "1~12자리를 입력해주세요.") //최소 1자리 최대 12자리
    @NotBlank
    private String password;

    @NotNull //문자가 아니라서 블랭크 필요없음.
    @Min(value = 1)
    @Max(value = 90)
    private int age;

    @Email //이메일 형식이 아니면 에러
    private String email;

    @PhoneNumber //만든 어노테이션!
//    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",message = "휴대폰 양식에 맞지 않습니다. 010-0000-0000") //풀어보면 2~3자리 숫자 - 3~4자리 숫자 - 4자리 숫자
    private String phoneNumber; //이게 받을때도 이렇게 받는게 아니라 phone_number로 받는다.

    @FutureOrPresent(message = "시간 데이터가 잘못되었습니다.") //현재보다 미래여야함. 이건 엑셉션 종류가 다름.
    private LocalDateTime registeredAt;

    @AssertTrue(message = "name or nickName 둘중 하나는 있어야 합니다.") //이걸로 둘중 하나는 있어야한다거나 그런거 할수있음. 트루 아니면 에러터지게 하는거니까.
//    @AssertFalse 이건 반대임. false여야함.
    // is로 시작해야 함. true인지 확인하는거임. 시작이 get이나 이상한거면 인식못함.
    public boolean isNameCheck(){

        if(Objects.nonNull(name) && !name.isBlank()){
            return true;
        }

        if(Objects.nonNull(nickName) && !nickName.isBlank()){
            return true;
        }

        return false;

    }
}
