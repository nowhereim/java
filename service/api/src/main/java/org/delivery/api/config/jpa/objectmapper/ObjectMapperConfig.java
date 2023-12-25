package org.delivery.api.config.jpa.objectmapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() { //이걸 설정안해주면 스프링부트가 디폴트로 제공하는 오브젝트매퍼가 빈으로 등록됨. 그래서 이걸 등록해줘야 내맘대로 설정가능
        var objectMapper = new ObjectMapper();

        objectMapper.registerModule(new Jdk8Module()); //jdk8이후 버전 클래스 지원

        objectMapper.registerModule(new JavaTimeModule()); //java.time 패키지 지원 locat date  time

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //모르는 프로퍼티가 있어도 무시하고 진행
        //이거 안하면 티끌만 틀려도 에러터짐

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); //  빈객체를 json으로 변환할때 에러가 나지 않도록 함.

        objectMapper.disable((SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)); //날짜를 타임스탬프로 변환하지 않고 ISO 포맷으로 변환

        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy()); //스네이크 케이스로 변환

        return objectMapper;

    }

    }
