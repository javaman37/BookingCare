package com.fx.asm3.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fx.asm3.service.CustomUserDetailsService;
import com.fx.asm3.service.JWTService;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JWTService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    
	
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        
       // Log để kiểm tra Authorization header
        System.out.println("Authorization Header: " + authHeader);
        
        
       if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader,"Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
       
        jwt = authHeader.substring(7);
        
        // Log để kiểm tra token đã trích xuất
        System.out.println("JWT Token: " + jwt);

        
        userEmail = jwtService.extractUserName(jwt);
        
        // Log để kiểm tra email người dùng được trích xuất từ token
        System.out.println("Extracted User Email: " + userEmail);
        
        if(StringUtils.isNotEmpty(userEmail)&& SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);
            
            if(jwtService.isTokenValid(jwt,userDetails)){
            	UsernamePasswordAuthenticationToken token = 
                		new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities()
                );
                
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
             // Thiết lập trực tiếp vào SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(token);

            }
        }
        filterChain.doFilter(request,response);
    }
    
    
}
