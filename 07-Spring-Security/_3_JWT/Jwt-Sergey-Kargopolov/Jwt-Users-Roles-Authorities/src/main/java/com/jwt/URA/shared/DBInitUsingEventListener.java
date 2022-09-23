package com.jwt.URA.shared;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.URA.entity.AuthorityEntity;
import com.jwt.URA.entity.RoleEntity;
import com.jwt.URA.entity.UserEntity;
import com.jwt.URA.repository.AuthorityRepository;
import com.jwt.URA.repository.RoleRepository;
import com.jwt.URA.repository.UserRepository;

@Component
public class DBInitUsingEventListener {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Utils utils;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener
	@Transactional
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("\n\n\n ...   ...   EVENT LISTENER TRIGGERED ... ...   \n\n\n ");

		AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
		AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
		AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");

//		RoleEntity userRole = createRole("ROLE_USER", Arrays.asList(readAuthority, writeAuthority));
		RoleEntity adminRole = createRole("ROLE_ADMIN", Arrays.asList(readAuthority, writeAuthority, deleteAuthority));

		if (adminRole == null)
			return;

		UserEntity adminUser = new UserEntity();
		adminUser.setFirstName("odel");
		adminUser.setLastName("shalem");
		adminUser.setEmail("odel@gmail.com");
		adminUser.setEmailVerificationStatus(true);
		adminUser.setUserId(utils.generateUserId(30));
		adminUser.setEncryptedPassword(passwordEncoder.encode("123"));
		
		adminUser.setRoles(Arrays.asList(adminRole));
		
		userRepository.save(adminUser);
	}

	@Transactional
	private AuthorityEntity createAuthority(String name) {

		AuthorityEntity authorityEntity = authorityRepository.findByAuthorityName(name);
		if (authorityEntity == null) {
			authorityEntity = new AuthorityEntity(name);
			authorityRepository.save(authorityEntity);
		}
		return authorityEntity;
	}

	@Transactional
	private RoleEntity createRole(String name, Collection<AuthorityEntity> authorities) {

		RoleEntity roleEntity = roleRepository.findByRoleName(name);
		if (roleEntity == null) {
			roleEntity = new RoleEntity(name);
			roleEntity.setAuthorities(authorities);
			roleRepository.save(roleEntity);
		}
		return roleEntity;
	}
}
