package com.jpa.security.audit;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuditorAwareImpl implements AuditorAware<String> {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public Optional<String> getCurrentAuditor() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			LOGGER.error("Auditing process " + authentication.getName() + " not found...");
			throw new UsernameNotFoundException("Auditing process " + authentication.getName() + " not found...");
		}

		// this is the user that will be updated in DB, as the one who created , update, modified etc...		
		return Optional.of(authentication.getName());
	}
}