package org.delivery.api.config.jpa.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    //스프링이 오브젝트맵퍼 빈을 찾아 여기다 넣어서 실행해줄거임.
    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper) { //이렇게 해줘야 오브젝트맵퍼가 빈으로 등록됨. 우리가 직접 등록한 오브젝트 맵퍼.
        return new ModelResolver(objectMapper);
    }
}
