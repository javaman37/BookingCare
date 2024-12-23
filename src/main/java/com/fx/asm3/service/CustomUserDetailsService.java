package com.fx.asm3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fx.asm3.config.CustomUserDetails;
import com.fx.asm3.dao.UserDAO;
import com.fx.asm3.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDAO.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

		// Kiểm tra trạng thái is_active trước khi trả về UserDetails
		if (!user.isActive()) {
			throw new DisabledException("User account is not active");
		}
		// Trả về đối tượng UserDetails bao gồm email, mật khẩu và quyền hạn
		return new CustomUserDetails(user);
	}

}
