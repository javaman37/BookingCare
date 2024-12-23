package com.fx.asm3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fx.asm3.config.CustomUserDetails;
import com.fx.asm3.dao.UserDAO;
import com.fx.asm3.dto.JwtAuthResponse;
import com.fx.asm3.dto.UserLoginDTO;
import com.fx.asm3.dto.UserRegistrationDTO;
import com.fx.asm3.entity.User;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    
	@Autowired
	private final UserDAO userDAO; // Inject userDAO
	
	private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder
	
	@Autowired
	private final AuthenticationManager authenticationManager;
	
	@Autowired
	private final JWTService jwtService;
	
	@Autowired
	private final EmailService emailService;
	

	@Override
	public User registerNewUser(UserRegistrationDTO userDTO) throws Exception {
		// Kiểm tra xem email đã tồn tại chưa
		if (userDAO.existsByEmail(userDTO.getEmail())) {
			throw new Exception("Email đã tồn tại!");
		}

		// Kiểm tra mật khẩu và xác nhận mật khẩu
		if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
			throw new Exception("Mật khẩu và xác nhận mật khẩu không khớp!");
		}

		// Tạo đối tượng User mới
		User user = new User();
		user.setName(userDTO.getName());
		user.setGender(userDTO.getGender());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setAddress(userDTO.getAddress());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Mã hóa mật khẩu
		user.setRole(userDTO.getRole());
		// Lưu người dùng vào cơ sở dữ liệu
		return userDAO.save(user);
	}

	
	
	@Override
	public JwtAuthResponse login(UserLoginDTO userLoginDTO) {
		//lấy thông tin đăng nhập
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						userLoginDTO.getEmail(), 
						userLoginDTO.getPassword()));
		//lấy thông tin ng dùng từ email
		var user = userDAO.findByEmail(userLoginDTO.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("sai email hoặc mật khẩu"));
        
		// Chuyển đổi đối tượng `User` thành `CustomUserDetails`
	    CustomUserDetails userDetails = new CustomUserDetails(user);
	    
	   // Tạo token JWT bằng cách truyền `userDetails` vào phương thức `generateToken`
		var jwt = jwtService.generateToken(userDetails);
		
		// Tạo đối tượng `JwtAuthResponse` để trả về cho người dùng
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setToken(jwt);
		return jwtAuthResponse;
	}



	public void sendForgotPasswordEmail(String email) {
	    User user = userDAO.findByEmail(email)
	        .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));

	    String token = jwtService.generateResetToken(user);
	    emailService.sendResetPasswordEmail(user.getEmail(), token);
	}



	@Override
	public void resetPassword(String token, String newPassword) {
		// Step 1: Find the user by reset token (this can be implemented depending on how your JWT or token logic is set up)
	    String email = jwtService.extractUserName(token);
	    User user = userDAO.findByEmail(email)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid token or email not found"));

	    // Step 2: Validate token (ensure token is not expired, etc.)
	    CustomUserDetails userDetails = new CustomUserDetails(user);
	    if (!jwtService.isTokenValid(token, userDetails)) {
	        throw new IllegalArgumentException("Invalid or expired token");
	    }

	    // Step 3: Encrypt the new password
	    String encodedPassword = passwordEncoder.encode(newPassword);

	    // Step 4: Update the user's password
	    user.setPassword(encodedPassword);

	    // Step 5: Save the updated user to the database
	    userDAO.save(user);
		
	}

}
