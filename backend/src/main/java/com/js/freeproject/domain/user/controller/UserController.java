package com.js.freeproject.domain.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.js.freeproject.domain.user.appliction.UserService;
import com.js.freeproject.domain.user.domain.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			userService.createUser(user);
		} catch(Exception e) {
			log.info("사용자 생성 중 오류 발생");
			return ResponseEntity.status(500).body(user);
		}
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/user/all")
	public ResponseEntity<List<User>> userList() {
		List<User> users = userService.userList();
		return ResponseEntity.ok(users);
	}
}
