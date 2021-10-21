package com.js.freeproject.domain.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.util.LogUtil;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
	@Autowired
	MockMvc mvc;
	
	@Autowired
	private ObjectMapper OM;
	
	@Test
	@Order(1)
	void CreateUser() throws Exception {
		LogUtil.Msg("사용자 생성","정상 생성");
		
		String email = "various@naver.com";
		String name = "홍길동";
		String nickName = "홍길동의 후예";
		
		String user = OM.writeValueAsString(User.builder()
				.email(email)
				.name(name)
				.nickName(nickName)
				.build());
		mvc.perform(post("/user")
				.content(user)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	@Order(2)
	void DuplCreateUser() throws Exception {
		LogUtil.Msg("중복 사용자 생성","에러 발생");
		
		
		
		String email = "various@naver.com";
		String name = "홍길동";
		String nickName = "홍길동의 후예";
		
		String user = OM.writeValueAsString(User.builder()
				.email(email)
				.name(name)
				.nickName(nickName)
				.build());
		mvc.perform(post("/user")
				.content(user)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(500));
		
		mvc.perform(get("/user/all")).andDo(print());
	}
}
