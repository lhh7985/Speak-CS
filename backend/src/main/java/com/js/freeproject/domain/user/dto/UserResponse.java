package com.js.freeproject.domain.user.dto;

import com.js.freeproject.domain.user.domain.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {
	private Long id;
	private String email;
	private String nickname;
	private String name;
	private String image;
	private String token;
	
	public UserResponse(final User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.nickname = user.getNickName();
		this.name = user.getName();
		this.image = user.getImage();
	}
	
	public UserResponse(final User user,final String token) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.nickname = user.getNickName();
		this.name = user.getName();
		this.image = user.getImage();
		this.token = token;
	}
}
