package com.jpa.one2many.bi.lazy.dto;

public class RoleDto {

	private String role;
	private long pid;

	public RoleDto() {
		super();
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

}
