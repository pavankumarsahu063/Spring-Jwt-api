package com.jwt.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
	
	private final String SECRET="secretKey";
	
	public String getJwtFromHeader(HttpServletRequest request) {
		String bearerToken=request.getHeader("Authorization");
		
		if(bearerToken != null && bearerToken.startsWith("Bearer ")){
				return bearerToken.substring(7);
	}
		return null;

	}
	
	public String generateTokenFromUserName(UserDetails userDetails) {
		String username=userDetails.getUsername();
		
		SecretKey key=Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();

	}
	
	public String getUserNameFromToken(String token) {
		SecretKey key=Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJwt(token)
				.getBody()
				.getSubject();
	}
	
	   public boolean isTokenExpired(String token) {
		   SecretKey key=Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
	        Date expiration = Jwts.parserBuilder()
	                .setSigningKey(key)
	                .build()
	                .parseClaimsJws(token)
	                .getBody()
	                .getExpiration();

	        return expiration.before(new Date());
	    }
	   public boolean validateToken(String token, UserDetails userDetails) {
	        final String username = getUserNameFromToken(token);
	        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	    }
	
}
