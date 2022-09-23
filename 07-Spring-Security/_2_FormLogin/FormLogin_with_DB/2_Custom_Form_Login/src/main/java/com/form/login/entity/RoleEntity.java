package com.form.login.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles_tb")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = -905082255078120896L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String role;

	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private Set<UserEntity> users;

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

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

}
