package com.js.freeproject.domain.mail.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Mail {
	private String to;
	private String title;
	private String content;
	private String type;
	
	@Builder
	public Mail(String to, String title, String content) {
		this.to = to;
		this.title = title;
		this.content = content;
	}
}