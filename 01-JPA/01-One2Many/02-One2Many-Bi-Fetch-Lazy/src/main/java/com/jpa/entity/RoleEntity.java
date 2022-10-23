package com.jpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ROLES_TB")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 4547155074103443567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String role;
	private long pid;

	// I add the "foreignKey = @ForeignKey(name = "fk_shabtay_shalem_test")" just 
	// For learning purpose , this will add CONTRAINTS to the foreign key in DB
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_shabtay_shalem_test"))
	@JsonIgnore
	private UserEntity user;

	public RoleEntity() {
		super();
	}

	public RoleEntity(String role) {
		super();
		this.role = role;
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

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", role=" + role + ", pid=" + pid + "]";
	}

}
