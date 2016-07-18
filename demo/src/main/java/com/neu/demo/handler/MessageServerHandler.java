package com.neu.demo.handler;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageServerHandler extends SimpleChannelUpstreamHandler{

	private static final Logger LOG = LoggerFactory.getLogger(MessageServerHandler.class);
	
	public Map<String,IHandler> handlers = new HashMap<String,IHandler>();
	
	public MessageServerHandler(Map<String,IHandler> map){
		this.handlers = map;
	}
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		IHandler handler = handlers.get(e.getMessage().getClass().getName());
		if(handler != null){
			handler.doHandler(ctx, e);
		}else{
			LOG.info("没有对应的handler" + e.getMessage().getClass().getName());
		}
	}
	
}
