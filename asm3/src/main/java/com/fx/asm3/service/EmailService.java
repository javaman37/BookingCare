package com.fx.asm3.service;

public interface EmailService {

	void sendResetPasswordEmail(String email, String token);

}
