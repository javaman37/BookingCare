package com.fx.asm3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fx.asm3.entity.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
	
    private static final Logger logger = LoggerFactory.getLogger(JWTService.class);

	
    @Override
    public String generateToken(UserDetails userDetails){
    	Map<String, Object> claims = new HashMap<>();
        // Lấy quyền của người dùng và thêm vào claims
        claims.put("roles", userDetails.getAuthorities());
    	
    	
        return Jwts.builder()
        		.setClaims(claims) // Thêm claims vào token
        		.setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 60 * 24))
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String extractUserName(String token){
    	String username = extractClaim(token, Claims::getSubject);
        logger.info("Extracted username from token: {}", username);
        return username;
    }
    
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers){
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }
    
    private Key getSiginKey(){
        byte[] key = Decoders.BASE64.decode("1232DWDERR43R343TRFFT3R455657Y5YTHBEGCD4D87CBE734HHEF98U4FHYH5");
        return Keys.hmacShaKeyFor(key);
    }
    
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
    }
    
    @Override
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    private boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

	@Override
	public String generateResetToken(User user) {
		return Jwts.builder()
	            .setSubject(user.getEmail())  // Use email as the subject for password reset
	            .setIssuedAt(new Date(System.currentTimeMillis()))
	            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  // Token valid for 1 hour
	            .signWith(getSiginKey(), SignatureAlgorithm.HS256)
	            .compact();
	}
}
