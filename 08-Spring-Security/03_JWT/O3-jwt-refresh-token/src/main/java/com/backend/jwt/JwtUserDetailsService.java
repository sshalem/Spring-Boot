package com.backend.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.entity.UserEntity;
import com.backend.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		// Important: Since I use Fetch.LAZY
		// Thus, I want to load here user with roles,
		// It's needed for the authentication process during Login
		// See the @Query in repository
		// Because roles are already loaded,
		// Spring Security can safely build GrantedAuthorities without triggering lazy loading outside the session.
		UserEntity userEntity = userRepo.findByEmailWithRoles(email);

		if (userEntity == null)
			throw new UsernameNotFoundException("user Email  :" + email + " not Exist");

		return new JwtUserDetails(userEntity);
	}

}
