package org.delivery.api.config.jpa.config;

import lombok.RequiredArgsConstructor;
import org.delivery.api.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthorizationInterceptor authorizationInterceptor;

    private List<String> OPEN_API_PATH = List.of(
            "/open-api/**"
    );

    private List<String> DEFAULT_EXCLUDE = List.of(
            "/",
            "/favicon.ico",
            "/error"
    );

    private List<String> SWAGGER = List.of(
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs"
    );


    //회원가입이나 로그인 혹은 약관동의 같은 경로는 인증이 필요없는 경로이기 때문에 인터셉터를 적용하지 않음. 하면 에러 터져버림.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/api/**")
//                .excludePathPatterns("/api/user/**")
//                .excludePathPatterns("/api/term/**"); //이런식으로 계속 붙이면 암걸림. 그래서 open 이런식으로 약속을 따로 만듦.
                .excludePathPatterns(OPEN_API_PATH) //이런식으로 따로 빼서 관리함.
                .excludePathPatterns(DEFAULT_EXCLUDE)
                .excludePathPatterns(SWAGGER);
    }
}
