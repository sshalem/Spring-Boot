package com.auth02.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth02.service.JwtUserDetailsService;
import com.auth02.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader(SecurityConstants.AUTHORIZATION);

		String username = null;
		String jwtToken = null;

		// to have only the JWT token we need to remove the Bearer prefix
		if (authorizationHeader != null && authorizationHeader.startsWith(SecurityConstants.BEARER_PREFIX)) {

			jwtToken = authorizationHeader.substring(7);

			try {
				username = jwtTokenUtil.extractUsernameFromToken(jwtToken);
				// Here I want to catch 2 exceptions which are thrown
				// from 'class ImmutableJwtParser implements JwtParser'
				// from method of 'parseClaimsJws():'
				// 1. IllegalArgumentException
				// 2. ExpiredJwtException
			} catch (IllegalArgumentException e) {
				LOGGER.error("JWT_TOKEN_UNABLE_TO_GET_USERNAME", e);
			} catch (ExpiredJwtException e) {
				LOGGER.warn("JWT_TOKEN_EXPIRED", e);
			}
		}

		LOGGER.debug("JWT_TOKEN_USERNAME_VALUE '{}'", username);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
			}

		}
		filterChain.doFilter(request, response);

	}
}
