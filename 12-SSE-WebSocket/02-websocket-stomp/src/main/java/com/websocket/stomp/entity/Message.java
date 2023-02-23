package com.websocket.stomp.entity;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1736015187911517445L;
	private String text;
	private String to;

	public Message() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "Message [text=" + text + ", to=" + to + "]";
	}

}
