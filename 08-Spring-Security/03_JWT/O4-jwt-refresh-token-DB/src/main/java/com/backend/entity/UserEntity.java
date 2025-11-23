package com.backend.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS_TB")
public class UserEntity {

	/** Since I use data-xxxx.sql files for DB's 
	 * Thus this is the preffered generator to use with each on:
	 * h2 DB : @GeneratedValue(strategy = GenerationType.IDENTITY)
	 * 
	 * MySql DB : @GeneratedValue(strategy = GenerationType.IDENTITY)
	 * 
	 * With PostGresql DB :     
	 * 	@SequenceGenerator(name = "studentseq", initialValue = 20001, allocationSize = 50)
	 *	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentseq")
	 *
	 * if we look in the data.postgres.sql we can see that the id's satrt from 1 for each table
	 * With postgresql , even though i have user id's from 1-10 , when I attempt to create a new record w/o specifiyng the ID
	 * It pulls the value from sequence (1) thus i can get unique violation
	 */
	@Id
	@SequenceGenerator(name = "userseq", initialValue = 20001, allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userseq")
	@Column(name = "user_id")	
	private long id;
	private String name;
	private String email;
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<RefreshTokenEntity> refreshTokens = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_role",
			joinColumns = { @JoinColumn(name = "user_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@JsonIgnore
	private Set<RoleEntity> roles = new HashSet<>();

	public UserEntity() {
		super();
	}

	public UserEntity(String name, String email, String password, Set<RoleEntity> roles) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	/*********************************************
	 * Helper Methods for Adding/Removing Role
	 *********************************************/

	public void addRole(RoleEntity roleEntity) {
		this.roles.add(roleEntity);
		roleEntity.getUsers().add(this);
	}

	public void removeRole(RoleEntity role) {
		this.roles.remove(role);
		role.getUsers().remove(this);
	}

	/*********************************************************
	 * 	Helper Methods for Adding/Removing refreshTokens
	 *********************************************************/
	public void addRefreshToken(RefreshTokenEntity refreshTokenEntity) {
		if (this.refreshTokens == null) {
			this.refreshTokens = new HashSet<>();
		}
		this.refreshTokens.add(refreshTokenEntity);
		refreshTokenEntity.setUserEntity(this);
	}

	public void removeRefreshToken(RefreshTokenEntity refreshTokenEntity) {
		this.refreshTokens.remove(refreshTokenEntity);
		refreshTokenEntity.setUserEntity(null); // break relationship properly
	}
		
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return id == other.id;
	}
	
}
