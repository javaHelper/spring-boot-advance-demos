package com.example.demo.config;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.entity.AppUserRole;
import com.example.demo.exception.CustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMilliseconds = 3600000; // 1h
	
	@Autowired
	private MyUserDetails myUserDetails;
	
	public String createToken(String username, List<AppUserRole> appUserRoles) {
		Claims claims = Jwts.claims().setSubject(username);
	    claims.put("auth", appUserRoles
				.stream()
				.map(s -> new SimpleGrantedAuthority(s.getAuthority()))
				.filter(Objects::nonNull).collect(Collectors.toList()));

	    Date now = new Date();
	    Date validity = new Date(now.getTime() + validityInMilliseconds);
	    
	    return Jwts.builder()//
	            .setClaims(claims)//
	            .setIssuedAt(now)//
	            .setExpiration(validity)//
	            .signWith(SignatureAlgorithm.HS256, secretKey)//
	            .compact();
	}
	
	
	public String getUsername(String token) {
	    return Jwts.parser()
	    		.setSigningKey(secretKey)
	    		.parseClaimsJws(token)
	    		.getBody()
	    		.getSubject();
	  }
	
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = myUserDetails.loadUserByUsername(this.getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
