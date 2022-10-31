package com.jpa.one2one.bi.lazy.entity;

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

	/**
	 * I set Cascade only for MERGE REFRESH DETACH PERSIST W/O remove , because I
	 * want to be able to : 1. Remove Only child Entity w/o removing parent 2.
	 * Remove Only Parent Entity w/o removing child
	 * 
	 * Don't add any kind of CASCADE type to the child Entity
	 */
	@OneToOne(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH,
			CascadeType.PERSIST }, fetch = FetchType.LAZY)
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
		this.address = addressEntity;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

}
