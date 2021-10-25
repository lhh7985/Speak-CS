package com.js.freeproject.domain.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.freeproject.domain.user.appliction.UserService;
import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.global.jwt.CustomUserDetails;
import com.js.freeproject.global.jwt.TokenProvider;
import com.sun.jdi.request.DuplicateRequestException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody User reqUser) {
		String email = reqUser.getEmail();
		String pass = reqUser.getPass();
		
		try {
			User dbUser = userService.findByUserEmail(email);
			
			if(dbUser==null) {
				log.info("사용자 정보가 없습니다.");
				return ResponseEntity.status(404).body("Not Found Email!"); 
			} else if(!passwordEncoder.matches(pass,dbUser.getPass())) {
				log.info("비밀번호가 일치하지 않습니다.");
				return ResponseEntity.status(401).body("Incorrect Password!");
			}
		} catch(Exception e) {
			log.info("사용자 로그인 중 오류 발생");
			return ResponseEntity.status(500).body("Server Error!");
		}
		
		return ResponseEntity.ok(TokenProvider.getToken(email));
	}
	
	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			userService.createUser(user);
		} catch(DuplicateRequestException e) {
			log.info("이미 가입된 이메일입니다.");
			return ResponseEntity.status(401).body(user);
		} catch(Exception e) {
			log.info("사용자 생성 중 오류 발생");
			return ResponseEntity.status(500).body(user);
		}
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/me")
	public ResponseEntity<User> getUserInfo(Authentication authentication) {
		CustomUserDetails accountDetailes = (CustomUserDetails) authentication.getDetails();
		
		User user = userService.findByUserEmail(accountDetailes.getUseremail());
		
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> userList() {
		List<User> users = userService.userList();
		return ResponseEntity.ok(users);
	}
}
