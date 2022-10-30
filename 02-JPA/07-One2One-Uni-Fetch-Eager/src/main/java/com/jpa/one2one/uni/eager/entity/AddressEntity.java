package com.jpa.one2one.uni.eager.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS_TB")
public class AddressEntity {

	@Id
	private Long id;
	private String street;
	private String city;

	@OneToOne(fetch = FetchType.EAGER)
	@MapsId
	@JoinColumn(name = "user_id")
	private UserEntity user;

	public AddressEntity() {
	}

	public AddressEntity(String street, String city) {
		super();
		this.street = street;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
