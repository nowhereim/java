package com.example.producingwebservice.controller;

import com.example.producingwebservice.model.BookQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j //이거 쓰면 로그를 찍을 수 있음. 그냥 콘솔에 찍는거랑 다른점은 로그백이라는게 있어서 로그를 찍을때마다 버퍼에 쌓아놓고 한번에 찍어줌. 그래서 속도가 빠름.
@RestController // 이 클래스는 REST API를 처리하는 컨트롤러 라고 알려주는 어노테이션
@RequestMapping("/api") // RequestMapping은 URI를 지정해주는 어노테이션
public class RestApiController {
    String html = "<html><body><h1>Hello World!</h1></body></html>";
    @GetMapping(path = "/hello") // GetMapping은 Get 요청을 받을 수 있는 API를 만들어주는 어노테이션
    public String hello() {
        return html;
    }

    @GetMapping(path= "/echo/{message}")
    public String echo(@PathVariable String message) {
        return message;
    }
    //아니면 이렇게도 할 수 있음 @PathVariable(message = "message") String message

    //params로 파라미터를 지정해줄 수 있다.
    @GetMapping(path = "/plus/{x}/{y}")
    public int plus(@PathVariable int x, @PathVariable int y) {
        return x + y;
    }

    @GetMapping(path = "/query")
    public String query(@RequestParam(name = "name",required = false) String name, @RequestParam(name = "age",required = false) Integer age) {
        try{
            if(age == null) {
                return "나이를 입력해주세요.";
            };
        String ret = name.toUpperCase();
        return "이름" + ret + "나이는 = " + age;
        } catch (Exception e) {
            e.printStackTrace();
            return "잘못된 입력입니다.";
        }
    }
    //nest랑은 다르게 그냥 경로만 지정하고 어노테이션으로 리퀘스트 파람을 지정해서 받아온다.
    //쿼리url은 /api/query?name=이름&age=나이 이런식으로 쿼리를 보내면 된다.

    @GetMapping(path = "/book")
    public String book(BookQueryParam 값){ //이걸 DTO로 쓰는것같음. 그대로 받아서 그대로 뱉고있는 느낌임.
        System.out.println(값.getSign());
        return 값.toString();
    }

    //딜리트는 그냥 여기다 박아버리자.
    @DeleteMapping(path = {
                "/user/{name}/delete", //이거 아니면
                "/user/{name}/del" //이거
        }
    )
    public void delete(
            @PathVariable("name") String name //박아줘야함.url에 있는 변수를 받아서 박아주는거임.
    ) {
        System.out.println(name + "이걸 기준으로 삭제해주세용");
        log.info("user-name : {}",name);
        //hello world를 찍어주는데 ???
    }
}
