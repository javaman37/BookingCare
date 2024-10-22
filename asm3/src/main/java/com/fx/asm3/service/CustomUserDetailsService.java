package com.fx.asm3.service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
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
	private  UserDAO userDAO; 

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDAO.findByEmail(email)
		        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // Trả về đối tượng UserDetails bao gồm email, mật khẩu và quyền hạn
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            true,
            true,
            true,
            true,
            Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
	}
	
	

}
