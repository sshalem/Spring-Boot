package com.in28min.Jwt.entity;

import java.io.Serializable;
import java.util.Date;

public class Todo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6775323946770783774L;
	private long id;
	private String username;
	private String description;
	private Date targetDate;
	private boolean _isDone;

	public Todo() {
		super();
	}

	public Todo(long id, String username, String description, Date targetDate, boolean _isDone) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this._isDone = _isDone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean is_isDone() {
		return _isDone;
	}

	public void set_isDone(boolean _isDone) {
		this._isDone = _isDone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", _isDone=" + _isDone + "]";
	}

}
