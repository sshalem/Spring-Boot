package com.websocket.stomp.entity;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1736015187911517445L;
	private String message;

	public Message() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [message=" + message + "]";
	}

}
