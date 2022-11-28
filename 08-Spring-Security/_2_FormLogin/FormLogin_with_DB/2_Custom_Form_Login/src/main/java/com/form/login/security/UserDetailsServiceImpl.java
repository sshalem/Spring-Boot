package com.form.login.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.form.login.entity.UserEntity;
import com.form.login.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userReposiotry;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userReposiotry.findByUsername(username);
		if (userEntity == null)
			throw new UsernameNotFoundException(username);

		return new UserDetailsImpl(userEntity);
	}

}
