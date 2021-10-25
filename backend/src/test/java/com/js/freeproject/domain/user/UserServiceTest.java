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
		String pass = "qwe123";
		
		String user = OM.writeValueAsString(User.builder()
				.email(email)
				.name(name)
				.pass(pass)
				.nickName(nickName)
				.build());
		
		mvc.perform(post("/user")
				.content(user)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(401));
	}
	
	@Test
	@Order(2)
	void DuplCreateUser() throws Exception {
		LogUtil.Msg("중복 사용자 생성","에러 발생");
		
		String email = "various@naver.com";
		String name = "임꺽정";
		String nickName = "임꺽정의 후예";
		String pass = "123qwe";
		
		String user = OM.writeValueAsString(User.builder()
				.email(email)
				.name(name)
				.pass(pass)
				.nickName(nickName)
				.build());
		mvc.perform(post("/user")
				.content(user)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(401));
	}
	
	@Test
	@Order(3)
	void ExitLogin() throws Exception {
		LogUtil.Msg("홍길동 로그인","정상 로그인");
		
		String email = "various@naver.com";
		String pass = "qwe123";
		
		String account = OM.writeValueAsString(User.builder()
				.email(email)
				.pass(pass)
				.build());
		
		mvc
		.perform(post("/user/login")
				.content(account)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	@Order(4)
	void InvalidEmailLogin() throws Exception {
		LogUtil.Msg("홍길동 로그인","이메일 에러");
		
		String email = "various@daum.net";
		String pass = "qwe123";
		
		String account = OM.writeValueAsString(User.builder()
				.email(email)
				.pass(pass)
				.build());
		
		mvc
		.perform(post("/user/login")
				.content(account)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(404));
	}
	
	@Test
	@Order(5)
	void InvalidPassLogin() throws Exception {
		LogUtil.Msg("홍길동 로그인","비밀번호 에러");
		
		String email = "various@naver.com";
		String pass = "qqqqqq";
		
		String account = OM.writeValueAsString(User.builder()
				.email(email)
				.pass(pass)
				.build());
		
		mvc
		.perform(post("/user/login")
				.content(account)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().is(401));
	}
	
	@Test
	@Order(6)
	void ExitUsertMe() throws Exception {
		LogUtil.Msg("존재하는 유저 정보","성공");
		String email = "various@naver.com";
		String pass = "qwe123";
		
		String user = OM.writeValueAsString(User.builder()
				.email(email)
				.pass(pass)
				.build());
		
		
		String token = mvc
		.perform(post("/user/login")
				.content(user)
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn().getResponse().getContentAsString();
		
		LogUtil.Msg("tokenInfo : ", token);
		
		mvc
		.perform(get("/user/me")
				.header("Authorization", "Bearer " + token))
		.andExpect(status().isOk());
	}
	
	@Test
	@Order(7)
	void AdminLogin() throws Exception {
		LogUtil.Msg("관리자 로그인","정상 로그인");
		
		String email = "admin";
		String pass = "admin";
		
		String account = OM.writeValueAsString(User.builder()
				.email(email)
				.pass(pass)
				.build());
		
		mvc
		.perform(post("/user/login")
				.content(account)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
}
