package com.js.freeproject.domain.mail.application;

import org.springframework.stereotype.Service;

import com.js.freeproject.domain.mail.domain.Mail;
import com.js.freeproject.global.util.MailUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {
	private final MailUtil mailUtil;
	
	public void sendMail(String to, String type) throws Exception {
		String title = "SCPC 이메일 인증입니다.";
		Mail mail = Mail.builder()
				.title(title)
				.to(to)
				.content(getcontent(type))
				.build();
		System.out.println(mail.toString());
		try {
			mailUtil.SendMail(mail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getcontent(String type) {
		StringBuilder content = new StringBuilder();
		content.append("<!DOCTYPE html>");
		content.append("<html>");
		content.append("<head></head>");
		content.append("<body>");
		content.append("<div>");
		if(type.equals("pass")) {
			content.append("비밀번호 찾기 전송");
			content.append("<a href='http://localhost'>비밀번호 찾기 링크</a>");
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

