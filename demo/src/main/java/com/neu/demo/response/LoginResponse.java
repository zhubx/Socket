package com.neu.demo.response;

import java.util.List;

public class LoginResponse {

	private String userid;
	
	private byte messagetype;
	
	private short messagelength;
	
	private byte result;
	
	private short onlinnum;
	
	private List<String> onlinuser;
	

	public final String getUserid() {
		return userid;
	}

	public final void setUserid(String userid) {
		this.userid = userid;
	}

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

	public final short getOnlinnum() {
		return onlinnum;
	}

	public final void setOnlinnum(short onlinnum) {
		this.onlinnum = onlinnum;
	}

	public final List<String> getOnlinuser() {
		return onlinuser;
	}

	public final void setOnlinuser(List<String> onlinuser) {
		this.onlinuser = onlinuser;
	}
	
}
