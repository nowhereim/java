package org.delivery.api.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
public class LoggerFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //실행 전
        log.info("doFilter");

        var req = new ContentCachingRequestWrapper((HttpServletRequest) request); //request를 감싸서 캐싱해줌.
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response); //response를 감싸서 캐싱해줌.

        chain.doFilter(req, res); //이렇게 캐싱된 데이터를 넘겨주어서 뒷단에서 읽을 수 있게 해줌.
        //실행 후
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder(); //스트링빌더는 스트링을 더할 때마다 새로운 객체를 만들지 않고 기존 객체에 더해줌.

        headerNames.asIterator().forEachRemaining(headerKey -> {
            var headerValue = req.getHeader(headerKey);
            headerValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append(" , ")
                    .append("]");
        });

        var requestBody = new String(req.getContentAsByteArray()); //캐싱된 바이트를 스트링으로 변환
        var uri = req.getRequestURI();
        var metohd = req.getMethod();
        log.info(">>>>>>>>>>>>>[REQUEST] uri : {} , method : {} , headerValues : {} requestBody : {}",uri,metohd, headerValues, requestBody );

        // response 정보
        var responseHeaderValues = new StringBuilder();
        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);
            responseHeaderValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append(" , ")
                    .append("]");
        });

        // response body
        var responseBody = new String(res.getContentAsByteArray());
        log.info("<<<<<<<<<<<<<[RESPONSE] uri : {} , method : {} , headerValues : {} responseBody : {}",uri,metohd, responseHeaderValues, responseBody);

        res.copyBodyToResponse(); //캐싱된 데이터를 다시 응답으로 보내줌. 이거 안하면 클라이언트단에서는 데이터 못받음.
    }


}
