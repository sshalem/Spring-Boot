package com.backend.jwt;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.backend.config.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 3540583232420968407L;

	private final Logger LOGGER = Logger.getLogger(JwtTokenUtil.class);

	@Value("${jwt.signing.key}")
	private String secretKey;

	public boolean validateToken(String token, UserDetails userDetails) {
		boolean isTokenExpired = extractClaim(token).getExpiration().before(new Date());
		final String username = extractUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired);
	}

	public String extractUsernameFromToken(String token) {
		return extractClaim(token).getSubject();
	}

	public String generateToken(UserDetails userDetails) {

		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		Map<String, Object> claims = new HashMap<>();		
		return Jwts
				.builder()
				.setClaims(claims) // claims - It's a hash map where we can define several details
				.setSubject(userDetails.getUsername()) // Subject - this is the user name
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	}

	private Claims extractClaim(String token) {

		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		
		try {
			Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return claimsJws.getBody();		
		} catch (RuntimeException ex) {
			LOGGER.error("An exception occured, while extracting claims in JwtTokenUtil");
			throw new RuntimeException(ex.getMessage());
		}		
	}

}
