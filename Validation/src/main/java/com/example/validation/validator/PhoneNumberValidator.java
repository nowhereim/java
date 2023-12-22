package com.example.validation.validator;

import com.example.validation.annotation.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private String regexp;
    @Override
    public void initialize(PhoneNumber constraintAnnotation) { //초기화를 시킬때 어노테이션에 달려있는 디폴트 정규식을 가져옴.
       this.regexp = constraintAnnotation.regexp(); //어노테이션 안에있는 regexp를 가져옴.
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) { //이게 실제로 검증하는 로직


        boolean result = Pattern.matches(regexp, s); //정규식과 s를 비교해서 맞으면 true 틀리면 false가 result에 담김.

        return false;
    }
    //이즈벨리드가 실행되며 value 즉 변수 여기서는 phoneNumber가 String s로 넘어오는거임.
}