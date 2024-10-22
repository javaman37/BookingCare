package com.fx.asm3.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fx.asm3.dto.ForgotPasswordDTO;
import com.fx.asm3.dto.JwtAuthResponse;
import com.fx.asm3.dto.ResetPasswordDTO;
import com.fx.asm3.dto.UserLoginDTO;
import com.fx.asm3.dto.UserRegistrationDTO;
import com.fx.asm3.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userDTO) {
        try {
        	authenticationService.registerNewUser(userDTO);
            return ResponseEntity.ok("Đăng ký thành công!"); // Trả về phản hồi thành công
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Trả về lỗi nếu có
        }
    }
    
    //Đăng nhập tài khoản
    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestBody UserLoginDTO userLoginDto) {
        try {
        	
            JwtAuthResponse jwt = authenticationService.login(userLoginDto);
            return ResponseEntity.ok(jwt);
        } catch (BadCredentialsException e) {
            logger.error("Bad credentials provided: ", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Thông tin đăng nhập không chính xác");
        } catch (UsernameNotFoundException e) {
            logger.error("User not found: ", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Người dùng không tồn tại");
        } catch (Exception e) {
            logger.error("Unexpected error during login: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi không xác định");
        }
    }
    
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        try {
            authenticationService.sendForgotPasswordEmail(forgotPasswordDTO.getEmail());
            return ResponseEntity.ok("Email xác nhận đã được gửi đi.");
        } catch (Exception e) {
            logger.error("Error occurred while sending forgot password email: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi gửi email xác nhận.");
        }
    }
    
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        try {
            authenticationService.resetPassword(resetPasswordDTO.getToken(), resetPasswordDTO.getNewPassword());
            return ResponseEntity.ok().body("Mật khẩu đã được đặt lại thành công.");
        } catch (Exception e) {
            logger.error("Error occurred while resetting password: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi đặt lại mật khẩu.");
        }
    }



    
    

}
