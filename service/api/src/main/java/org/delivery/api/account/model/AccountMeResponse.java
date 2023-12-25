package org.delivery.api.account.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
//@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) //이거 일일이 다 적으려면 죽을 수 있음. 그래서 config에다가 적어줌. 그러면 모든 dto에 적용됨.
public class AccountMeResponse {

    private String email;

    private String name;

    private LocalDateTime registeredAt;
}
