package com.jpa.security.model;

import java.io.Serializable;
import java.util.UUID;

public class UserRegisterResponse implements Serializable {

	private static final long serialVersionUID = -6510303737259643281L;

	private UUID id;
	private String name;
	private String email;

	public UserRegisterResponse() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

}
