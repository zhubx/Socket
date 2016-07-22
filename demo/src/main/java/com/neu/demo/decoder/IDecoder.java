package com.neu.demo.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;

public interface IDecoder {

	public Object doDecode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer);
}
