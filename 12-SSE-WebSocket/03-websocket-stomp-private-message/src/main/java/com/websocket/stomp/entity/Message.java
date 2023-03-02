package com.websocket.stomp.entity;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1736015187911517445L;
	private String senderName;
	private String receiverName;
	private String message;

	public Message() {
		super();
	}

	public Message(String senderName, String receiverName, String message) {
		super();
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.message = message;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Message [senderName=" + senderName + ", receiverName=" + receiverName + ", message=" + message + "]";
	}

}
