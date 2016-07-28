package com.neu.client.bean;

public class ServerMessage extends ProtocolMsg{

	private String sender;
	
	private byte type;
	
	private String content;
	
	private String incept;

	public final String getSender() {
		return sender;
	}

	public final void setSender(String sender) {
		this.sender = sender;
	}

	public final byte getType() {
		return type;
	}

	public final void setType(byte type) {
		this.type = type;
	}

	public final String getContent() {
		return content;
	}

	public final void setContent(String content) {
		this.content = content;
	}
	
	public final String getIncept() {
		return incept;
	}

	public final void setIncept(String incept) {
		this.incept = incept;
	}

	@Override
	public String toString() {
		return "ServerMessage [sender=" + sender + ", type=" + type
				+ ", content=" + content + "]";
	}

	public String msgDetail() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
