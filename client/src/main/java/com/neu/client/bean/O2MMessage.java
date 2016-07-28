package com.neu.client.bean;

public class O2MMessage extends ProtocolMsg{

	private String sender;
	
	private String message;

	public final String getSender() {
		return sender;
	}

	public final void setSender(String sender) {
		this.sender = sender;
	}

	public final String getMessage() {
		return message;
	}

	public final void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "O2MMessage [sender=" + sender + ", message=" + message + "]";
	}
	
}
