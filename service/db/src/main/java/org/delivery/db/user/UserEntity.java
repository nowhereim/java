package org.delivery.db.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.user.enums.UserStatus;

import java.time.LocalDateTime;

@Entity // ()여기다 벨류 = name 해도대나요? 여기다 쓰는 이름을 테이블에서 찾음.
@Table(name = "user") // 이것도 마찬가지임. 이거 안쓰고 엔티티에 벨류에 이름을 넣어도 상관없음.
@Data
@EqualsAndHashCode(callSuper = true) //부모 클래스의 필드까지 같이 비교해줌.
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserEntity extends BaseEntity {

    @Column(nullable = false, length = 50, unique = false)
    private String name;

    @Column(nullable = false, length = 100, unique = false)
    private String email;

    @Column(nullable = false, length = 100, unique = false)
    private String password;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(nullable = false, length = 150, unique = false)
    private String address;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;
}
