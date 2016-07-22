package com.neu.demo.encoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.response.O2OMessageResponse;

/**
 * 一对一消息反馈编码
 * @author zhubx
 *
 */
public class O2OMessageResponseEncoder implements IEncoder{

	private static final Logger LOG = LoggerFactory.getLogger(O2OMessageResponseEncoder.class);
	
	public ChannelBuffer doEncoder(ChannelHandlerContext ctx, Channel channel,
			Object msg) {
		O2OMessageResponse message = (O2OMessageResponse)msg;
		LOG.info("[O2OMessageResponseEncoder]" + message);
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeByte(message.getCommandId());
		buffer.writeShort(message.getMsglength());
		buffer.writeByte(message.getResult());
		return buffer;
	}

	 
}
