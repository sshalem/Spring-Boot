package com.basic.auth.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users_tb")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	private String email;

//	@ManyToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
//	@JoinTable(name = "users_roles", 
//			   joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"), 
//			   inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JsonIgnore
	private Set<RoleEntity> roles;

	public UserEntity() {
		super();
	}
 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}
	
	public void addRole(RoleEntity role) {
		if(this.roles == null) {
			this.roles = new HashSet<>();
		}
		this.roles.add(role);
		role.setUser(this);
	}

	public void removeRole(RoleEntity role) {
		this.roles.remove(role);
		role.setUser(this);
	}
	
//	public void addRole(RoleEntity roleEntity) {
//		if (this.roles == null)
//			this.roles = new HashSet<>();
//		this.roles.add(roleEntity);
//		roleEntity.getUsers().add(this);
//	}
//
//	public void removeRole(RoleEntity roleEntity) {
//		this.roles.remove(roleEntity);
//		roleEntity.getUsers().remove(this);
//	}
	
}
