package com.neu.demo.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

import com.neu.demo.bean.Message;
import com.neu.demo.response.MessageResponse;

public class MessageHandler implements IHandler{

	public void doHandler(ChannelHandlerContext ctx, MessageEvent e) {
		// TODO Auto-generated method stub
		Message message = (Message)e;
		
		MessageResponse response = new MessageResponse();
		
		response.setMessagetype((byte)0x8002);
		
		e.getChannel().write(response);
		
	}

}
