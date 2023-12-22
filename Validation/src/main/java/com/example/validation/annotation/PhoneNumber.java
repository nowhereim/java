package com.example.validation.annotation;

import com.example.validation.validator.PhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.*;

@Constraint(validatedBy = PhoneNumberValidator.class) //이 어노테이션을 검증할때 사용할 클래스를 적어줌.
@Target({ElementType.FIELD}) //필드에만 붙일수 있게
@Retention(RetentionPolicy.RUNTIME) //런타임까지 유지하겠다.
//@NotBlank//이런식으로 어노테이션을 걸수있다.
//@Size(min = 1, max = 12 , message = "1~12자리를 입력해주세요.") //이런식으로 어노테이션을 걸수있다.
public @interface PhoneNumber {

    String message() default "핸드폰 번호 양식에 맞지 않습니다. ex) 010-0000-0000";
    String regexp() default "^\\d{2,3}-\\d{3,4}-\\d{4}$";

    //String pattern() default "yyyyMMdd"

    Class<?>[] groups() default {}; //이거랑 밑에게 디폴트로 들어가있어야하나봄.

    Class<? extends jakarta.validation.Payload>[] payload() default {};
}

//이건 어노테이션이고 이 어노테이션을 쓸때 검증할 클래스를 지정해줘야함.
//내부에는 메세지랑 정규식이 들어있음.
//그리고 이 어노테이션을 쓸때 필드에만 붙일수 있게 해놨음.