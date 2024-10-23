package com.fx.asm3.service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(), 
				user.getPassword(), 
				true,   // Account is non-expired
		        true,   // Credentials are non-expired
		        true,   // Account is non-locked
		        true,   // Account is enabled (ở đây nếu tài khoản bị vô hiệu hóa thì sẽ ko trả về true)
				Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
	}

}
