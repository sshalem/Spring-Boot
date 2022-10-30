package com.jpa.one2one.bi.eager.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_TB")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -5199469587304114249L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;
	private boolean published;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private AddressEntity address;

	public UserEntity() {
		super();
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

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity addressEntity) {
		if (addressEntity == null) {
			if (this.address != null) {
				this.address.setUser(null);
			}
		} else {
			this.address.setUser(this);
		}
		this.address = addressEntity;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	/**
	 * public void setDetails(PostDetails details) { 
	 * 	if (details == null) { 
	 * 		if(this.details != null) {
	 * 			this.details.setPost(null);
	 * 		} 
	 * 	} else {
	 * 		details.setPost(this); 
	 * 	} 
	 * 	this.details = details; 
	 * }
	 */

}
