package com.js.freeproject.global.util;

import javax.mail.MessagingException;
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
	
	public void SendMail(Mail mail) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message);
		messageHelper.setTo(mail.getTo());
		messageHelper.setFrom("CSPS");
		messageHelper.setSubject(mail.getTitle());
		messageHelper.setText(mail.getContent(),true);
		
		javaMailSender.send(message);
	}
	
	public String getcontent(String type,String key) {
		StringBuilder content = new StringBuilder();
		content.append("<!DOCTYPE html>");
		content.append("<html>");
		content.append("<head></head>");
		content.append("<body>");
		content.append("<div>");
		if(type.equals("pass")) {
			content.append("비밀번호 찾기 전송");
			content.append("<a href='http://localhost:8080/user/fixpass?key="+key + "'>비밀번호 찾기 링크</a>");
		} else if(type.equals("mail")) {
			content.append("메일 인증 전송");
			content.append("<a href='http://localhost'>메일 인증 링크</a>");
		}
		content.append("</div>");
		content.append("</body>");
		content.append("</html>");
		return content.toString();
	}
}
