package com.js.freeproject.domain.mail.application;

import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import com.js.freeproject.domain.mail.domain.Mail;
import com.js.freeproject.global.util.MailUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {
	private final MailUtil mailUtil;
	
	public void sendMail(String to,String type) throws MessagingException  {
		String key = UUID.randomUUID().toString();
		
		Mail mail = Mail.builder()
				.title("SCPC 이메일 인증입니다.")
				.to(to)
				.content(mailUtil.getcontent(type,key))
				.build();
		
		mailUtil.SendMail(mail);
	}
	
	
}

