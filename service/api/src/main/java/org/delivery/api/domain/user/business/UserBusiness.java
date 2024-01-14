package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.contorller.model.UserRegisterRequest;
import org.delivery.api.domain.user.contorller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class UserBusiness {
    private final UserService userService;
    private final UserConverter userConverter;

    //사용자에 대한 가입처리 로직
    //1. request -> entity
    //2. entity -> save
    //3. save entity -> response
    //4. response return
    public UserResponse register(UserRegisterRequest request) {

        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userConverter.toResponse(newEntity);

        return response;

        //코드 수준은 누구나 다 똑같지 않다. 더 명시적이고 알아보기 쉬운 위 방식을 선호할 수 있다.



//        return Optional.ofNullable(request)
//                .map(userConverter::toEntity)
//                .map(userService::register) //이런식으로 전부 람다식으로 쓸수도있음.
//                .map(userConverter::toResponse)
//                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINTER_EXCEPTION,"UserRegisterRequest is null"));

    }
}
// 컨트롤러 => 비지니스 => 서비스 => 레포지토리 이런식으로 될거임.

//서비스는 도메인 로직을 처리하는 곳이다. 비지니스쪽에서 할게 만다고함.