package com.neu.demo.bean;

/**
 * one to one message
 * @author zhubx
 *
 */
public class O2OMessage extends ProtocolMsg{

	private String incept;
	
	private String message;
	
	private String sender;

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

	public final String getSender() {
		return sender;
	}

	public final void setSender(String sender) {
		this.sender = sender;
	}

	@Override
	public String toString() {
		return "O2OMessage [incept=" + incept + ", message=" + message
				+ ", sender=" + sender + "]";
	}
 
}
