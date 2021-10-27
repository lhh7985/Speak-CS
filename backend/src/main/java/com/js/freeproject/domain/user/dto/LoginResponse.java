package com.js.freeproject.domain.user.dto;

import com.js.freeproject.domain.model.CommonResponse;

import lombok.Getter;

@Getter
public class LoginResponse extends CommonResponse {
	private String token;
	
	public LoginResponse() {}

	public static LoginResponse of(String message, String token) {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.message = message;
		loginResponse.token = token;
		return loginResponse;
	}
}
