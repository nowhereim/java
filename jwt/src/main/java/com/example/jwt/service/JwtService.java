package com.example.jwt.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Slf4j
@Service
public class JwtService {
    private static String secretKey = "secreadsfqwerewqrewqradwsfasdfzxcvzcxvdsaf";

    public String create(
            Map<String, Object> claims, //클레임은 토큰에 담을 정보
            LocalDateTime expireAt //토큰 만료 시간
    ) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
        var _expireAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant()); //LocalDateTime -> Date
        var token = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256) //키를 이용해 암호화
                .setClaims(claims) //클레임즈 설정
                .setExpiration(_expireAt) //만료 시간 설정
                .compact(); //토큰 생성

        System.out.println("토큰 = " + token);


        return token;
    }

    public void validate(String token) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());


        var parser = Jwts.parserBuilder() //파서 생성
                .setSigningKey(key) //키 설정
                .build();


        try {
            //여기서 예외를 잡음.
            var result = parser.parseClaimsJws(token); //토큰 파싱
            result.getBody().entrySet().forEach(it-> log.info("key : {} , value : {}", it.getKey(), it.getValue()));
        }catch(ExpiredJwtException e) {
            if(e instanceof JwtException) {
               throw new RuntimeException("첫번째 에러다링");

            } else if (e instanceof ExpiredJwtException) {
                throw new RuntimeException("토큰이 만료되었습니다.");

            }else {
                throw new RuntimeException("알 수 없는 에러가 발생했습니다.");
            }
        }



        }


}
