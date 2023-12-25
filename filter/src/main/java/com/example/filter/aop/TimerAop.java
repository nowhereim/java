package com.example.filter.aop;

import com.example.filter.model.UserRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect //AOP로 동작하겠다.
@Component //스프링 빈으로 등록하겠다.
public class TimerAop {




    //스프링에서 관리되고있는 빈 에만 Aop가 동작하게 되어있음.
    //Bean이 아니라 다른곳에 적용하고 싶다면? aspectj를 사용하면 됨.
    @Pointcut(value = "within(com.example.filter.controller.UserApiController)") //com.example.filter.controller.UserApiController 안에 있는 모든 메소드에 적용하겠다.
    public void timerPointCut(){}

    @Before(value = "timerPointCut()")
    public void before(
            JoinPoint joinPoint //JoinPoint는 메소드의 정보를 가지고 있음.
    ){
        System.out.println("before");
    }

    @After(value = "timerPointCut()")
    public void after(
            JoinPoint joinPoint //JoinPoint는 메소드의 정보를 가지고 있음.
    ){
        System.out.println("after");
    }

    @AfterReturning(value = "timerPointCut()", returning = "returnObj") //returnObj는 리턴값을 받아올 수 있음.
    public void afterReturning(
            JoinPoint joinPoint, //JoinPoint는 메소드의 정보를 가지고 있음.
            Object returnObj //리턴값을 받아올 수 있음.
    ){
        System.out.println("returnObj = " + returnObj);
    }

    @AfterThrowing(value = "timerPointCut()", throwing = "ex") //예외를 받아올 수 있음.
    public void afterThrowing(
            JoinPoint joinPoint, //JoinPoint는 메소드의 정보를 가지고 있음.
            Throwable ex //예외를 받아올 수 있음.
    ){
        System.out.println("ex = " + ex.getMessage());
    }

    @Around(value = "timerPointCut()") // 어떤 포인트컷을 적용할 것인지 적어줌. 이건 예외발생 여부와 상관없이 실행됨.
    public void around(ProceedingJoinPoint joinPoint) throws Throwable //ProceedingJoinPoint는 메소드의 정보를 가지고 있음.
    {
        //이게 필터랑 다른점은 메소드 실행시점을 기준으로 잡아낼수있다는거임.
        System.out.println("메소드 실행 이전");

        Arrays.stream(joinPoint.getArgs()).sequential().forEach(
                it->{
                    if(it instanceof UserRequest){
                        UserRequest tempUser = (UserRequest)it;
                        //들어온 값을 받아서 폰넘버 안에있는 - 을 전부 제거해준 다음 반환해주는거임.
                        String phoneNumber = tempUser.getPhoneNumber().replace("-","");
                        tempUser.setPhoneNumber(phoneNumber);

                    }
                    System.out.println("it = " + it);
                }
        ); //메소드의 매개변수들을 가져올 수 있음.

        // 암/복호화 / 로깅 / 트랜잭션 / 특정 api 혹은 서비스가 느릴때 aop로 걸어버리면 편하게 확인가능. 응용범위가 넓다.
        var newObj = Arrays.asList(
                new UserRequest()
        );

        var stopWatch = new StopWatch();
        stopWatch.start();

        //트라이캐치로 예외 잡아낼수있음.
        joinPoint.proceed(newObj.toArray()); //proceed를 호출해야 메소드가 실행됨.
        //여기서 바꿔치기를 하면 전달받은 메소드는 바꿔친 값으로 실행됨.

        stopWatch.stop();
        System.out.println("total time : " + stopWatch.getTotalTimeSeconds()); //총 실행시간을 알 수 있음.
        System.out.println("메소드 실행 이후");
    }

}
