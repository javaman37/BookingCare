package com.fx.asm3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
    private JavaMailSender mailSender;

	@Override
	public void sendResetPasswordEmail(String email, String token) {
		String resetLink = "http://localhost:8080/api/auth/reset-password?token=" + token;
	    String subject = "Reset Your Password";
	    String message = "Click the link to reset your password: " + resetLink;

	    // Gửi email qua dịch vụ đã cấu hình
	    sendEmail(email, subject, message); // Phương thức sendEmail() đã có trong EmailServiceImpl
		
	}

	public void sendEmail(String to, String subject, String body) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(to);
	    message.setSubject(subject);
	    message.setText(body);
	    mailSender.send(message);
	}
}
