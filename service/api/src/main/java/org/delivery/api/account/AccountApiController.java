package org.delivery.api.account;

import lombok.RequiredArgsConstructor;
import org.delivery.api.account.model.AccountMeResponse;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.api.Result;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.account.AccountEntity;
import org.delivery.db.account.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {
    private final AccountRepository accountRepository;  //동일한 경로가 아님. 그래서 config로 엔티티 스캔 , 레포지토리 스캔해서 빈으로 등록해줘야함.

    @GetMapping("")
    public void save() {
        var account = AccountEntity.builder().build();

        accountRepository.save(account);
    }

    ;

    @GetMapping("/me")
    public Api<AccountMeResponse> me() {
        AccountMeResponse response = AccountMeResponse.builder()
                .name("한라봉")
                .email("ttt@naver.com")
                .registeredAt(LocalDateTime.now())
                .build();





        return Api.OK(response);
    }

    @GetMapping("/err")
    public Api<AccountMeResponse> err() {
        AccountMeResponse response = AccountMeResponse.builder()
                .name("한라봉")
                .email("ttt@naver.com")
                .registeredAt(LocalDateTime.now())
                .build();

        var str = "안녕하세요";
        try{
            var age = Integer.parseInt(str); //이런식으로 에러를 발생시킬 수 있음. (NumberFormatException
        }catch (Exception e){
            throw new ApiException(ErrorCode.SERVER_ERROR,e , "사용자 Me 호출하다가 에러 개박살나버림.");
        }






        //요청은 무조건 성공 아니면 실패임. 트라이캐치로 낑낑 잡을 필요없음. 터지면 핸들러로 처리하면 된다. 스택트레이스 잘 띄우고 클라이언트에게는
        //간결하게 실패를 알려주면 됨.

        return Api.OK(response);
    }
}
