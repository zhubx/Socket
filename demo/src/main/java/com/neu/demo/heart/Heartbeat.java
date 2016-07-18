package com.neu.demo.heart;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Heartbeat extends IdleStateAwareChannelHandler{

	private static final Logger LOG = LoggerFactory.getLogger(Heartbeat.class);
			
	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e)
			throws Exception {
		if(e.getState() == IdleState.READER_IDLE) {
			e.getChannel().close();
			LOG.info(e.getChannel() + "超时断开.");
		}
		
	}
}
