package com.basic.auth.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.basic.auth.entity.RoleEntity;
import com.basic.auth.entity.UserEntity;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -3710423577585001036L;
	private UserEntity userEntity;

	public UserDetailsImpl(UserEntity userEntity) {
		super();
		this.userEntity = userEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<RoleEntity> roles = this.userEntity.getRoles();

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		roles.forEach(role -> {
			SimpleGrantedAuthority e = new SimpleGrantedAuthority("ROLE_" + role.getRole());
			authorities.add(e);
		});

		return authorities;
	}

	@Override
	public String getPassword() {
		return this.userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		return this.userEntity.getUsername();
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
		return true;
	}

}
