package com.jwt.URA.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jwt.URA.entity.AuthorityEntity;
import com.jwt.URA.entity.RoleEntity;
import com.jwt.URA.entity.UserEntity;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1396304826996631375L;
	private UserEntity userEntity;

	public UserPrincipal() {
		super();
	}

	public UserPrincipal(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> userAuthorities = new ArrayList<>();

		List<AuthorityEntity> authoritiesEntities = new ArrayList<>();

		Collection<RoleEntity> roles = this.userEntity.getRoles();

		if (roles == null)
			return userAuthorities;

		roles.forEach(role -> {
			userAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			authoritiesEntities.addAll(role.getAuthorities());
		});

		authoritiesEntities.forEach(authorityEntity -> {
			userAuthorities.add(new SimpleGrantedAuthority(authorityEntity.getAuthorityName()));
		});
		
		return userAuthorities;
	}

	@Override
	public String getPassword() {
		return this.userEntity.getEncryptedPassword();
	}

	@Override
	public String getUsername() {
		return this.userEntity.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.userEntity.isEmailVerificationStatus();
	}

}
