package com.backend.jwt;

import java.io.Serializable;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.backend.config.SecurityConstants;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 3540583232420968407L;

	private final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

	@Value("${jwt.signing.key}")
	private String secretKey;


	public String extractUsernameFromToken(String token) {
		// subject is the user-name , 
		// in our case I use email as user-name
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
	}
		

	public String generateToken(UserDetails userDetails) {

		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		Map<String, Object> claims = new HashMap<>();
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		
		claims.put("roles", authorities);
		
		/**
		 * This is another implementation , the Payload of the Token will be different
		 * 
		 * List<RoleEntity> listOfRoles = roleRepository.findAll();
		 * listOfRoles.forEach(role -> {
		 * 		if(authorities.contains(new SimpleGrantedAuthority("ROLE_" + role.getRole())))
		 * 			claims.put("is" + role.getRole(), true);
		 * 	}); 
		 */
		
		
		return Jwts
				.builder()
				.setHeaderParam("type", "JWT") // this is the Header of the token
				.setClaims(claims) // claims - It's a hash map where we can define several details
				.setSubject(userDetails.getUsername()) // Subject - this is the user name
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.JWT_EXPIRATION_TIME_ms))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	}
	
	
	public String generateRefreshToken(UserDetails userDetails) {
		
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		
		// Since this is a refreshToken It doesn't have to be A bearer token
		
		return Jwts
				.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME_ms))
				.signWith(key,SignatureAlgorithm.HS512)
				.compact();
	}

	
	public boolean validateToken(String token) {
		
		Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
		
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;		
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			LOGGER.debug(ex.getMessage());
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			LOGGER.debug(ex.getMessage());
			throw ex;
		}
	}
}
