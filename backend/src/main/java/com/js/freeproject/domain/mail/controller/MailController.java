package com.js.freeproject.domain.mail.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.js.freeproject.domain.mail.application.MailService;
import com.js.freeproject.domain.model.CommonResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("mail")
@Api(value="Mail API")
public class MailController {
	private final MailService mailService;
	
	@PostMapping("send")
	@ApiOperation(value="이메일 발송",notes="email일경우 이메일인증, pass일경우 비밀번호 인증")
	@ApiResponses({
		@ApiResponse(code=200,message = "이메일 정상 발송",response = CommonResponse.class),
		@ApiResponse(code=500,message = "서버 오류",response = CommonResponse.class)
	})
	public ResponseEntity<?> sendMail(@RequestParam String mail, @RequestParam String type) {
		try {
			mailService.sendMail(mail,type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.status(200).body(CommonResponse.of("Success"));
	}
	
	@GetMapping("certified")
	@ApiOperation(value = "이메일 인증")
	@ApiResponses({
		@ApiResponse(code = 200, message = "이메일 인증 성공",response = CommonResponse.class),
		@ApiResponse(code = 500, message = "서버 오류", response = CommonResponse.class)
	})
	public ResponseEntity<?> checkMail() {
		return ResponseEntity.status(200).body(CommonResponse.of("Success"));
	}
}
