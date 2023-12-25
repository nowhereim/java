package com.example.filter.config;

import com.example.filter.Interceptor.OpenApiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //설정파일이라는 것을 알려주는 어노테이션
public class WebConfig implements WebMvcConfigurer {


    @Autowired //RequiredArgsConstructor를 쓰던 생성자를 직접 만들던 뭐 알아서 해도 상관없음.
    private OpenApiInterceptor openApiInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) { //인터셉터를 등록하는 곳
//        registry.addInterceptor(openApiInterceptor)
//                .addPathPatterns("/api/**") //api로 시작하는 모든 요청에 대해서 인터셉터를 적용하겠다.
//                .excludePathPatterns("/api/private-staff/**"); //이런 요청은 인터셉터를 적용하지 않겠다.


        //인터셉터는 등록 순서대로 동작함.
    }
}
