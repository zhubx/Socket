package com.neu.demo.bean;

public class Message {

	private byte messageType;
	
	private String incept;
	
	private String message;

	public final byte getMessageType() {
		return messageType;
	}

	public final void setMessageType(byte messageType) {
		this.messageType = messageType;
	}

	public final String getIncept() {
		return incept;
	}

	public final void setIncept(String incept) {
		this.incept = incept;
	}

	public final String getMessage() {
		return message;
	}

	public final void setMessage(String message) {
		this.message = message;
	}
	
	
}
