package com.neu.demo.response;

import com.neu.demo.bean.ProtocolMsg;

public class O2MMessageResponse extends ProtocolMsg{

	private byte result;

	public final byte getResult() {
		return result;
	}

	public final void setResult(byte result) {
		this.result = result;
	}

}
