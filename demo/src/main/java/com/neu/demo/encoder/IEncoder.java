package com.neu.demo.encoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;

public interface IEncoder {

	public ChannelBuffer doEncoder(ChannelHandlerContext ctx, Channel channel,
			Object msg);
}
