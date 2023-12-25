package org.delivery.db.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

@SuperBuilder //부모 클래스의 필드까지 같이 생성해줌. 빌더로 부모 클래스의 필드까지 접근 가능하다는 소리.
@Data
@EqualsAndHashCode(callSuper = true) //부모 클래스의 필드까지 같이 비교해줌.
@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {
}
