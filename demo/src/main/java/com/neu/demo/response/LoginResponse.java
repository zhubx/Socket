package com.neu.demo.response;

import java.util.List;

import com.neu.demo.bean.ProtocolMsg;

public class LoginResponse extends ProtocolMsg{

	private String userid;
	
	private byte result;
	
	private short onlinnum;
	
	private List<String> onlinuser;
	

	public final String getUserid() {
		return userid;
	}

	public final void setUserid(String userid) {
		this.userid = userid;
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
	
	public String msgDetail() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
