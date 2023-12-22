package com.example.producingwebservice;

import com.example.producingwebservice.model.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProducingWebServiceApplicationTests {

	@Autowired //이렇게 하면 스프링이 알아서 빈을 주입해줌.
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() throws JsonProcessingException {
		var user = new UserRequest("steve",10,"gmail",true);
//		user.setUserName("steve");
//		user.setUserAge(10);
//		user.setEmail("gmail");
//		user.setIsKorean(true);
		//objectMapper는 직렬화를 할때 변수명이 아니라 겟터를 찾아서 직렬화를 함. 그래서 겟터를 만들어줘야함.
		//get으로 시작하는 메서드를 찾아서 직렬화를 함. get뒤가 이름이된다고 보면된다. 즉 get으로시작하는 메서드가 있어야 오브젝트 매퍼가 직렬화를 할수있다.
		//objectMapper는 스프링이 알아서 빈으로 등록해줌. writeValueAsString은 json으로 바꿔줌.
		var json = objectMapper.writeValueAsString(user); //json으로 변환
		System.out.println(user);
		System.out.println(json);
		var str = objectMapper.readValue(json, UserRequest.class); //json을 객체로 변환.
		System.out.println(str);
	}

}
