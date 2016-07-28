package com.neu.demo.response;

import com.neu.demo.bean.ProtocolMsg;


public class HeartBeatResponse extends ProtocolMsg{

	private byte result;
	
	public final byte getResult() {
		return result;
	}



	public final void setResult(byte result) {
		this.result = result;
	}



	public String msgDetail() {
		// TODO Auto-generated method stub
		return null;
	}

}
