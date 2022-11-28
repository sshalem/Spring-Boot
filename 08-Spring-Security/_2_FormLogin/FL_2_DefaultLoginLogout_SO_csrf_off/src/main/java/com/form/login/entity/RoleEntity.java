package com.form.login.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles_tb")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = -905082255078120896L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String role;

//	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
//	private Set<UserEntity> users;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	public RoleEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

//	public Set<UserEntity> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Set<UserEntity> users) {
//		this.users = users;
//	}

}