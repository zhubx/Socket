package com.neu.client.bean;

import java.util.List;

public class LoginResponse {

	private byte messageType;
	
	private byte result;
	
	private short onlinnum;
	
	private List<String> userlist;

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

	public final List<String> getUserlist() {
		return userlist;
	}


	public final void setUserlist(List<String> userlist) {
		this.userlist = userlist;
	}


	@Override
	public String toString() {
		return "LoginResponse [messageType=" + messageType + ", result="
				+ result + ", onlinnum=" + onlinnum + ", userlist=" + userlist
				+ "]";
	}
	
	
}
