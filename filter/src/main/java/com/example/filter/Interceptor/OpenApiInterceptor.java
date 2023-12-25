package com.example.filter.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Component//인터셉터를 빈으로 등록
public class OpenApiInterceptor implements HandlerInterceptor {
    //Object Handler는 내가 어디로 가야할 핸들러 정보가 들어있음.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getRequestURI());
        log.info("인터셉터 진입 pre handle");
        //리턴 true면 컨트롤러 전달 , false면 전달 안함.

        if(!(handler instanceof HandlerMethod)){
            log.info("Open Api가 아니네 ㅋㅋ");
            return true;
        } //핸들러가 핸들러메서드가 아니면 그냥 통과시킴.(핸들러메서드는 컨트롤러에 있는 메서드
        HandlerMethod handlerMethod = (HandlerMethod)handler; //이걸 핸들러메서드로 캐스팅 시키면 메소드 레벨에 어노테이션을 확인할 수 있음.

        var methodLevel = handlerMethod.getMethodAnnotation(OpenApi.class); //메소드 레벨에 어노테이션이 있는지 확인
        if(methodLevel != null){
            log.info("methodLevel : {}", methodLevel);
            return true;
        }

        var classLevel = handlerMethod.getBeanType().getAnnotations(); //클래스 레벨에 어노테이션이 있는지 확인
        if(classLevel != null){
            log.info("classLevel : {}", classLevel);
            return true;
        }


        log.info("여긴 어케옴? 아니네 ㅋㅋ");
        return false;
    } //인터셉터의 로직을 작성하는 곳

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("인터셉터 진입 post handle");
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    } //컨트롤러가 끝나고 화면이 랜더링 되기 전에 호출

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("인터셉터 진입 after completion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    } //모든 작업이 완료된 후 호출
}
