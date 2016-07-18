package com.neu.demo.bean;

public class LoginMessage extends ProtocolMsg{

	private String userid;
	
	private String username;
	
	private String password;
	
	public LoginMessage(){}
	
	
	
	public LoginMessage(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public final String getUserid() {
		return userid;
	}

	public final void setUserid(String userid) {
		this.userid = userid;
	}

	public final String getUsername() {
		return username;
	}

	public final void setUsername(String username) {
		this.username = username;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}
	
}
