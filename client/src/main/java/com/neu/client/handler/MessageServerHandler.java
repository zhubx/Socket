package com.neu.client.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageServerHandler extends ChannelHandlerAdapter{

	private static final Logger LOG = LoggerFactory.getLogger(MessageServerHandler.class);
	
	public Map<String,IHandler> handlers = new HashMap<String,IHandler>();
	
	public MessageServerHandler(Map<String,IHandler> map){
		this.handlers = map;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		// TODO Auto-generated method stub
		IHandler handler = handlers.get(msg.getClass().getName());
		if(handler != null){
			handler.doHandler(ctx, msg);
		}else{
			LOG.info("[没有对应的handler]" + msg.getClass().getName());
		}
	}
	 
}
