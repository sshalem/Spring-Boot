package com.jwt.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jwt.entity.Role;
import com.jwt.entity.User;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -8319335788529025258L;
	private User user;

	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

//		Set<SimpleGrantedAuthority> collect = this.user.getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole())).collect(Collectors.toSet());
//
//		return collect;

		Set<SimpleGrantedAuthority> roles = new HashSet<>();

		Set<Role> userRolesFromDB = user.getRoles();

		userRolesFromDB.forEach(role -> {
			SimpleGrantedAuthority s = new SimpleGrantedAuthority("ROLE_" + role);
			roles.add(s);
		});

		return roles;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
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
