package org.delivery.api.interceptor;

import io.swagger.v3.oas.models.PathItem;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization Interceptor : {}" , request.getRequestURI());

        //WEB , chrom 의 경우 GET, POST OPTIONS  = pass
        if(HttpMethod.OPTIONS.matches(request.getMethod())){ // OPTIONS 메서드는 CORS에서 사전 요청을 위해 사용되는 메서드 ?
            return true;
        }

        // js. html. png. resource를 요청 하는 경우 = pass
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        //TODO header 검증

        return true; //일단 통과 처리

    }
}
