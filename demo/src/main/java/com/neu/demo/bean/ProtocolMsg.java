package com.neu.demo.bean;

public abstract class ProtocolMsg implements IProtocolMsg{

	private byte commandId;
	
	private short msglength;

	public final byte getCommandId() {
		return commandId;
	}

	public final void setCommandId(byte commandId) {
		this.commandId = commandId;
	}

	public final short getMsglength() {
		return msglength;
	}

	public final void setMsglength(short msglength) {
		this.msglength = msglength;
	}
	
}
