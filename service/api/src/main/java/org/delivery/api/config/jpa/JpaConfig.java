package org.delivery.api.config.jpa;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.delivery.db") //db 패키지 하위에 있는 엔티티를 찾아서 빈으로 등록해줌.
@EnableJpaRepositories(basePackages = "org.delivery.db") //db 패키지 하위에 있는 레포지토리를 찾아서 빈으로 등록해줌.
public class JpaConfig {
}
