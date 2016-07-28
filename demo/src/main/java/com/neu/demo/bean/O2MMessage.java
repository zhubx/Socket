package com.neu.demo.bean;

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

	
	public String msgDetail() {
		// TODO Auto-generated method stub
		return "O2MMessage [commandId=" + super.getCommandId() + ",msglength=" + 
				super.getMsglength() + ",sender=" + sender + ", message=" + message + "]";
	}
	
}
