package com.neu.demo.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import com.neu.demo.response.HeartBeatResponse;

public class HeartBeatHandler implements IHandler{

	public void doHandler(ChannelHandlerContext ctx, MessageEvent e) {
		// TODO Auto-generated method stub
		HeartBeatResponse response = new HeartBeatResponse();
		response.setCommandId((byte)0x0009);
		response.setMsglength((short)1);
		response.setResult((byte)0);
		e.getChannel().write(response);
	}

}
