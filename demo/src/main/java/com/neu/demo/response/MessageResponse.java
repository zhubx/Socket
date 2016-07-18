package com.neu.demo.response;

public class MessageResponse {

	private byte messagetype;
	
	private short messagelength;
	
	private byte result;

	public final byte getMessagetype() {
		return messagetype;
	}

	public final void setMessagetype(byte messagetype) {
		this.messagetype = messagetype;
	}

	public final short getMessagelength() {
		return messagelength;
	}

	public final void setMessagelength(short messagelength) {
		this.messagelength = messagelength;
	}

	public final byte getResult() {
		return result;
	}

	public final void setResult(byte result) {
		this.result = result;
	}
	
}
