package com.fx.asm3.service;

import com.fx.asm3.dto.JwtAuthResponse;
import com.fx.asm3.dto.UserLoginDTO;
import com.fx.asm3.dto.UserRegistrationDTO;
import com.fx.asm3.entity.User;

public interface AuthenticationService {
	
	User registerNewUser(UserRegistrationDTO userDTO) throws Exception;

	JwtAuthResponse login(UserLoginDTO userLoginDto);

	void sendForgotPasswordEmail(String email);

	void resetPassword(String token, String newPassword);

}
