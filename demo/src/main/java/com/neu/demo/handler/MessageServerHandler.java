package com.neu.demo.handler;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.bean.LoginMessage;
import com.neu.demo.cache.UserKeySession;
import com.neu.demo.cache.UserSession;

public class MessageServerHandler extends SimpleChannelUpstreamHandler{

	private static final Logger LOG = LoggerFactory.getLogger(MessageServerHandler.class);
	
	public Map<String,IHandler> handlers = new HashMap<String,IHandler>();
	
	private UserSession session = UserSession.getInstance();
	
	private UserKeySession keysession = UserKeySession.getInstance();
	
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
			LOG.info("[没有对应的handler]" + e.getMessage().getClass().getName());
		}
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		LOG.info("exceptionCaught");
		super.exceptionCaught(ctx, e);
	}
	
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
		LOG.info("channelDisconnected");
		if(e.getChannel() != null) {
			LoginMessage message = session.getLoginMessage(e.getChannel());
			//清除session 
			session.remove(message);
			keysession.remove(message.getUsername());
			
		}
		LOG.info("[MessageServerHandler]在线人数:" + keysession.size());
		e.getChannel().close().addListener(ChannelFutureListener.CLOSE);
	}
	
	/*
	 * 连接和断开顺序 1 channelOpen 2 channelBound  3 channelConnected 
	 *  4 channelDisconnected  5 channelUnbound  6 channelClosed
	 */
}
