package com.backend.util;

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
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 3540583232420968407L;
	
	private final Logger LOGGER = Logger.getLogger(JwtTokenUtil.class);

	@Value("${jwt.signing.key}")
	private String secretKey;

	public Boolean validateToken(String token, UserDetails userDetails) {
		Boolean isTokenExpired = extractClaim(token).getExpiration().before(new Date());
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
				.setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS512).compact();
	}

	private Claims extractClaim(String token) {

		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
		Claims body = claimsJws.getBody();
		return body;
	}

	public boolean validateJwtToken(String authToken) {
		try {
			extractClaim(authToken);
			return true;
		} catch (MalformedJwtException e) {			
			LOGGER.error("Invalid JWT token: " + e.getMessage());
		} catch (ExpiredJwtException e) {
			LOGGER.error("JWT token is expired: " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			LOGGER.error("JWT token is unsupported: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			LOGGER.error("JWT claims string is empty: " + e.getMessage());
		}

		return false;
	}
}
