package com.js.freeproject.domain.user.dto;

import com.js.freeproject.domain.model.CommonResponse;
import com.js.freeproject.domain.user.domain.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserResponse extends CommonResponse {
	private Long id;
	private String email;
	private String nickname;
	private String name;
	private String image;
	
	public static UserResponse of(String message,final User user) {
		UserResponse userResponse = new UserResponse();
		
		userResponse.message = message;
		userResponse.id = user.getId();
		userResponse.email = user.getEmail();
		userResponse.nickname = user.getNickName();
		userResponse.name = user.getName();
		userResponse.image = user.getImage();
		
		return userResponse;
	}
}
