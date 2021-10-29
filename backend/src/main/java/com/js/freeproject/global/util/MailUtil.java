package com.js.freeproject.global.util;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.js.freeproject.domain.mail.domain.Mail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailUtil {
	private final JavaMailSender javaMailSender;
	
	public void SendMail(Mail mail) throws Exception {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message);
		System.out.println("-----------------------------");
		System.out.println(mail.toString());
		messageHelper.setTo(mail.getTo());
		messageHelper.setFrom("CSPS");
		messageHelper.setSubject(mail.getTitle());
		messageHelper.setText(mail.getContent(),true);
		
		javaMailSender.send(message);
	}
}
