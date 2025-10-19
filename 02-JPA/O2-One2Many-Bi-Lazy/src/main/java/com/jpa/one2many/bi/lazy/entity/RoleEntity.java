package com.jpa.one2many.bi.lazy.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
	//	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_shabtay_shalem_test"))
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id" ,nullable = true)
	@JsonBackReference
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
		return "RoleEntity{" +
				"pid=" + pid +
				", role='" + role + '\'' +
				", id=" + id +
				'}';
	}
}
