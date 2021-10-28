package com.js.freeproject.domain.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import com.js.freeproject.domain.user.dto.LoginRequest;
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
		
		String email = "user@naver.com";
		String name = "사용자";
		String nickName = "사용자의 후예";
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
		.andExpect(status().is(200));
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
		
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail(email);
		loginRequest.setPass(pass);
		
		String account = OM.writeValueAsString(loginRequest);
		
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
		
		
		String login_res = mvc
		.perform(post("/user/login")
				.content(user)
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn().getResponse().getContentAsString();
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(login_res);
		JSONObject json = (JSONObject) obj;
		
		String token = (String) json.get("token");
		
		LogUtil.Msg("tokenInfo : ", token);
		
		mvc
		.perform(get("/user/me")
				.header("Authorization", "Bearer " + token))
		.andExpect(status().isOk())
		.andDo(print());
		
		/////////////////////////////////////////////////////////////////
		
		email = "admin";
		pass = "admin";
		
		String admin_user = OM.writeValueAsString(User.builder()
				.email(email)
				.pass(pass)
				.build());
		
		
		String admin_login_res = mvc
		.perform(post("/user/login")
				.content(admin_user)
				.contentType(MediaType.APPLICATION_JSON))
		.andReturn().getResponse().getContentAsString();
		
		JSONParser admin_parser = new JSONParser();
		Object admin_obj = admin_parser.parse(admin_login_res);
		JSONObject admin_json = (JSONObject) admin_obj;
		
		String admin_token = (String) admin_json.get("token");
		
		LogUtil.Msg("tokenInfo : ", token);
		
		mvc
		.perform(get("/user/me")
				.header("Authorization", "Bearer " + admin_token))
		.andExpect(status().isOk())
		.andDo(print());
		
		////////////////////////////////////////////////////////////////
		
		mvc
		.perform(get("/user/me")
				.header("Authorization", "Bearer " + token))
		.andExpect(status().isOk())
		.andDo(print());
		
//		mvc
//		.perform(get("/user/me"))
//		.andExpect(status().is(401));
//		
//		mvc
//		.perform(get("/user/me")
//				.header("Authorization", "Bearer " + "hihi"))
//		.andExpect(status().is(401));
	}
	
	@Test
	@Order(7)
	void DuplicateNickname() throws Exception {
		LogUtil.Msg("존재하는 닉네임 중복검사");
		
		mvc
		.perform(get("/user/홍길동의 후예"))
		.andExpect(status().is(401));
		
		LogUtil.Msg("존재하지 않는 닉네임 중복검사");
		
		mvc
		.perform(get("/user/우루루의 후예"))
		.andExpect(status().is(200));
	}
	
//	@Test
//	@Order(7)
//	void AdminLogin() throws Exception {
//		LogUtil.Msg("관리자 로그인","정상 로그인");
//		
//		String email = "admin";
//		String pass = "admin";
//		
//		String account = OM.writeValueAsString(User.builder()
//				.email(email)
//				.pass(pass)
//				.build());
//		
//		mvc
//		.perform(post("/user/login")
//				.content(account)
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(status().isOk());
//	}
}
