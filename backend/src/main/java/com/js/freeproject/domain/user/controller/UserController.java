package com.js.freeproject.domain.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.freeproject.domain.model.CommonResponse;
import com.js.freeproject.domain.user.appliction.UserService;
import com.js.freeproject.domain.user.domain.User;
import com.js.freeproject.domain.user.dto.LoginRequest;
import com.js.freeproject.domain.user.dto.LoginResponse;
import com.js.freeproject.domain.user.dto.UserRequest;
import com.js.freeproject.domain.user.dto.UserResponse;
import com.js.freeproject.global.jwt.CustomUserDetails;
import com.js.freeproject.global.jwt.TokenProvider;
import com.sun.jdi.request.DuplicateRequestException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(value="사용자 API")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping("login")
	@ApiOperation(value="사용자 로그인", notes ="아이디와 패스워드를 통해 로그인 한다.")
	@ApiResponses({
		@ApiResponse(code=200,message="성공",response = LoginResponse.class),
		@ApiResponse(code=401,message="인증 실패",response = CommonResponse.class),
		@ApiResponse(code=404,message="사용자 없음",response = CommonResponse.class),
		@ApiResponse(code=500,message="서버 오류",response = CommonResponse.class)
	})
	public ResponseEntity<?> loginUser(@RequestBody @ApiParam(value="로그인 정보",required = true) LoginRequest reqUser) {
		String email = reqUser.getEmail();
		String pass = reqUser.getPass();
		
		try {
			User dbUser = userService.findByUserEmail(email);
			
			if(dbUser==null) {
				log.info("사용자 정보가 없습니다.");
				return ResponseEntity.status(404).body(CommonResponse.of("존재하지 않는 계정입니다.")); 
			} else if(!passwordEncoder.matches(pass,dbUser.getPass())) {
				log.info("비밀번호가 일치하지 않습니다.");
				return ResponseEntity.status(401).body(CommonResponse.of("비밀번호가 일치하지 않습니다."));
			}
			
			String token = TokenProvider.generateToken(email);
			
			return ResponseEntity.ok(LoginResponse.of("Success", token));
		} catch(Exception e) {
			log.info("사용자 로그인 중 오류 발생");
			return ResponseEntity.status(500).body(CommonResponse.of("서버 오류가 발생했습니다."));
		}
	}
	
	@PostMapping()
	@ApiOperation(value = "사용자 생성")
	@ApiResponses({
		@ApiResponse(code=200,message="성공",response = CommonResponse.class),
		@ApiResponse(code=401,message="중복된 이메일",response = CommonResponse.class),
		@ApiResponse(code=500,message="서버 오류",response = CommonResponse.class)
	})
	public ResponseEntity<?> createUser(@RequestBody UserRequest user) {
		try {
			userService.createUser(user);
		} catch(DuplicateRequestException e) {
			log.info("이미 가입된 이메일입니다.");
			return ResponseEntity.status(401).body(CommonResponse.of("이미 가입된 이메일입니다."));
		} catch(Exception e) {
			log.info("사용자 생성 중 오류 발생");
			return ResponseEntity.status(500).body(CommonResponse.of("서버 오류가 발생했습니다."));
		}
		return ResponseEntity.ok(CommonResponse.of("Success"));
	}
	
	@GetMapping("me")
	@ApiOperation(value = "내 정보 가져오기")
	@ApiResponses({
		@ApiResponse(code=200,message="성공",response = UserResponse.class),
		@ApiResponse(code=500,message="서버 오류",response = CommonResponse.class)
	})
	public ResponseEntity<?> getUserInfo(Authentication authentication) {
		CustomUserDetails accountDetailes = (CustomUserDetails) authentication.getDetails();
		
		User user = userService.findByUserEmail(accountDetailes.getUseremail());
		
		return ResponseEntity.ok(UserResponse.of("Success", user));
	}
	
	@GetMapping("{nickName}")
	@ApiOperation(value="닉네임 중복검사",notes="존재하는 닉네임인지 확인한다.")
	@ApiResponses({
		@ApiResponse(code=200,message="성공",response = CommonResponse.class),
		@ApiResponse(code=401,message="중복된 닉네임",response = CommonResponse.class),
		@ApiResponse(code=500,message="서버 오류",response = CommonResponse.class)
	})
	public ResponseEntity<?> duplicateNickName(@PathVariable String nickName) {
		try {
			boolean duplnickname = userService.findByUserNickName(nickName);
			if(duplnickname) {
				log.info("{} 는 이미 사용하고 있는 닉네임입니다",nickName);
				return ResponseEntity.status(401).body(CommonResponse.of("이미 사용하고 있는 닉네임입니다."));
			}
			return ResponseEntity.ok(CommonResponse.of("Success"));
		} catch(Exception e) {
			log.info("{} 조회 중 오류 발생",nickName);
			return ResponseEntity.status(500).body(CommonResponse.of("서버 오류가 발생했습니다."));
		}	
	}
}
