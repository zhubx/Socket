package com.neu.demo.handler;

import java.util.List;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.bean.O2MMessage;
import com.neu.demo.bean.ServerMessage;
import com.neu.demo.cache.UserKeySession;
import com.neu.demo.response.O2MMessageResponse;

/**
 * 一对多消息handler
 * @author zhubx
 *
 */
public class O2MMessageHandler implements IHandler{

	private static final Logger LOG = LoggerFactory.getLogger(O2MMessageHandler.class);
	
	UserKeySession session = UserKeySession.getInstance();
	
	public void doHandler(ChannelHandlerContext ctx, MessageEvent e) {
		O2MMessage message = (O2MMessage)e.getMessage();
		LOG.info("[O2MMessageHandler]" + message);
		O2MMessageResponse response = new O2MMessageResponse();
		List<Channel> list = session.getAllChannel();
		//遍历所有在线用户下发消息
		for(Channel c : list){
			ServerMessage sm = new ServerMessage();
			sm.setCommandId((byte)0x0004);
			sm.setContent(message.getMessage());
			sm.setType((byte)1);
			sm.setSender(message.getSender());
			c.write(sm);
		}
		
		//返回结果
		response.setCommandId((byte)0x8003);
		response.setMsglength((short)1);
		response.setResult((byte)1);
		e.getChannel().write(response);
	}

}
