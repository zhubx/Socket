package com.neu.demo.handler;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.bean.O2OMessage;
import com.neu.demo.bean.ServerMessage;
import com.neu.demo.cache.UserKeySession;
import com.neu.demo.response.O2OMessageResponse;

/**
 * 一对一消息处理，
 * 流程：判断接受用户是否在线，如果在线，下发消息，返回发送成功
 * 如果不在线，返回发送失败
 * @author zhubx
 *
 */
public class O2OMessageHandler implements IHandler{

	private static final Logger LOG = LoggerFactory.getLogger(O2OMessageHandler.class);
	
	UserKeySession session = UserKeySession.getInstance();
	
	
	public void doHandler(ChannelHandlerContext ctx, MessageEvent e) {
		O2OMessage message = (O2OMessage)e.getMessage();
		LOG.info("[O2OMessageHandler]" + message);
		String ruser = message.getIncept();
		Channel channel = session.getChannel(ruser);
		O2OMessageResponse response = new O2OMessageResponse();
		response.setCommandId((byte)0x0004);
		response.setMsglength((short)1);
		if(channel !=null && channel.isConnected()){
			ServerMessage sm = new ServerMessage();
			sm.setCommandId((byte)0x0007);
			sm.setContent(message.getMessage());
			sm.setType((byte)2);
			sm.setSender(message.getSender());
			//发送消息给指定人
			channel.write(sm);
			
			//返回结果
			response.setResult((byte)0);
			
		}else{
			//用户不在线 返回结果
			LOG.info("[用户不在线]" + ruser);
			response.setResult((byte)2);
		}
		e.getChannel().write(response);
	}

}
