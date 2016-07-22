package com.neu.demo.bean;

public class ServerMessage extends ProtocolMsg{

	private String sender;
	
	private byte type;
	
	private String content;

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

	@Override
	public String toString() {
		return "ServerMessage [sender=" + sender + ", type=" + type
				+ ", content=" + content + "]";
	}
	
}
