package com.example.filter.Interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD , ElementType.TYPE}) //메소드와 타입에 붙일 수 있음.
@Retention(RetentionPolicy.RUNTIME) //런타임까지 유지하겠다.
public @interface OpenApi {
}
