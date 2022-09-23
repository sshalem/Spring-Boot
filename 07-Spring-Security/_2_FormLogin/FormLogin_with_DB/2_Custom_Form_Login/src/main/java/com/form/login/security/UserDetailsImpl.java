package com.form.login.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.form.login.entity.RoleEntity;
import com.form.login.entity.UserEntity;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -1850743808180546149L;
	private UserEntity userEntity;

	public UserDetailsImpl() {
		super();
	}

	public UserDetailsImpl(UserEntity userEntity) {
		super();
		this.userEntity = userEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		// We need to return a Collection of GrantedAuthority (The Implementation is at Class SimpleGrantedAuthority)
		// AT DB the Roles in DB are Stores as Set<RoleEntity> ,
		// we need to convert from Set<RoleEntity> to Set<SimpleGrantedAuthority>.
		// Class of SimpleGrantedAuthority has only one field : String role.
		// Class of RoleEntity has a field of : String role.
		// So we need to :
		// 		get the role from UserEntity getRole() 
		//		set the role at SimpleGrantedAuthority setRole()
		//  	We Must add the prefix of "ROLE_" once we set it at SimpleGrantedAuthority so it will for example : "ROLE_ADMIN"
		//	Question:
		// 		Why we need to add  prefix of "ROLE_"?
		// 	Answer:
		//		Because, by default spring security looks for a string that is prefixed with "ROLE_".
		// 		The code line below has the method 'hasRole()' : 
		// 			.antMatchers("/api/app/superadmin/**").hasRole(SecurityConstants.SUPER_ADMIN)
		//		If we press F3 on that method to see It's implementation we see that Spring add this prefix.
		//
		// Next steps show to to do that:
		
		// (1) Declare a new Set<SimpleGrantedAuthority> grantedRoles = new HashSet<>(); 
		//     which will hold the all Roles at the end , this is what we return back from the method
		Set<SimpleGrantedAuthority> grantedRoles = new HashSet<>();

		// (2) Get all Roles from DB, which say are stored in a Set<RoleEntity>
		Set<RoleEntity> rolesEntities = this.userEntity.getRoles();

		// (3) loop thru Set<RoleEntity> we got from DB
		// 	   create new SimpleGrantedAuthority instance , set SimpleGrantedAuthority role from the RoleEntity role.
		//	   add the new SimpleGrantedAuthority object to the Set<SimpleGrantedAuthority>
		rolesEntities.forEach(role -> {
			SimpleGrantedAuthority s = new SimpleGrantedAuthority("ROLE_" + role.getRole());
			grantedRoles.add(s);
		});

		return grantedRoles;
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
