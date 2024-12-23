package com.fx.asm3.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.fx.asm3.entity.User;

public interface JWTService {

	String extractUserName(String jwt);

	boolean isTokenValid(String jwt, UserDetails userDetails);

	String generateToken(UserDetails userDetails);

	String generateResetToken(User user);

}
