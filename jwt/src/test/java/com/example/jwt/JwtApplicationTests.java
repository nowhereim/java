package com.example.jwt;

import com.example.jwt.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;

@SpringBootTest
class JwtApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
	}


	@Test
	void tokenCreate() {
		var claims = new HashMap<String, Object>();
		claims.put("name", "John Doe");

		var expiredAt = LocalDateTime.now().plusMinutes(10);

		var jwtToken = jwtService.create(claims, expiredAt);
		System.out.println("jwtToken = " + jwtToken);
	}

	@Test
	void tokenValidation() {

		var token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiSm9obiBEb2UiLCJleHAiOjE3MDM0Njc2OTV9.6mwiP7Pg0ah77qZFSKJSkiMQmUWEnnmayNfOjk3GI9Q";
		jwtService.validate(token);

	}

}
