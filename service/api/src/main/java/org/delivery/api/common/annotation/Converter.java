package org.delivery.api.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // 클래스, 인터페이스, 열거형에 붙일 수 있음.
@Retention(RetentionPolicy.RUNTIME) // 런타임까지 유지하겠다.
@Service //스프링이 빈으로 등록해줌.
public @interface Converter {

    @AliasFor(annotation = Service.class) //스프링이 빈으로 등록해줌.
    String value() default "";
}
