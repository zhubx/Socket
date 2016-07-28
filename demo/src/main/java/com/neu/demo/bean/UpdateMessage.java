package com.neu.demo.bean;

public class UpdateMessage extends  ProtocolMsg{

	private byte type;
	
	private String name;

	public final byte getType() {
		return type;
	}

	public final void setType(byte type) {
		this.type = type;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public String msgDetail() {
		// TODO Auto-generated method stub
		return null;
	}

}
