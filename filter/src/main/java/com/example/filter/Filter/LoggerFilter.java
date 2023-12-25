package com.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.stream.Collectors;

//클라리언트가 "저는 잘 보냈는디요?"
//이걸 차단하기 위해 아래와같이 필터로 로우데이터를 확인해야함.

@Slf4j
//@Component//필터를 빈으로 등록
public class LoggerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { //필터가 초기화 될 때 호출
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 진입전
        log.info(">>>>>>>> 진입");
//        var req = new HttpServletRequestWrapper((HttpServletRequest) request);
////        var res = new HttpServletRequestWrapper((HttpServletRequest) response);
        var req = new ContentCachingRequestWrapper((HttpServletRequest) request); //스프링에서 제공하는 이 콘텐트캐싱은
        //읽어온 데이터를 캐싱해놓고 다시 읽어올 수 있게 해줌. 그래서 뒷단에서 읽어올 수 있음.
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);


//        var br = req.getReader(); //request.getInputStream()을 사용하면 바이트 코드로 읽어오기 때문에 문자열로 읽어오기 위해 BufferedReader를 사용
        //근데 여기서 미리 읽어버리는 바람에 이후 뒷단에서 해당 내용을 읽지못해 에러가 터져버림.
//        var list = br.lines().collect(Collectors.toList());

//        list.forEach(line -> {
//            log.info("line : {}", line);
//        });


        chain.doFilter(req, res); //다음 필터를 호출 여기 req , res를 넣어줘야 밑에 Json에서 찍히지.
        //진입후

        var reqJson = new String(req.getContentAsByteArray()); //캐싱된 데이터를 읽어옴.
        var resJson = new String(res.getContentAsByteArray());
        log.info("request uri : {}, request body : {}", req.getRequestURI(), reqJson);
        log.info("response status code : {}, response body : {}", res.getStatus(), resJson);
//        log.info("response status code : {}", response.getContentType());
        log.info(">>>>>>>> 나감");
        log.info(resJson);

        res.copyBodyToResponse(); //캐싱된 데이터를 다시 응답으로 보내줌. 이거 안하면 클라이언트단에서는 데이터 못받음.
    } //필터의 로직을 작성하는 곳

    @Override
    public void destroy() { //필터가 종료될 때 호출
        Filter.super.destroy();
    }
}
