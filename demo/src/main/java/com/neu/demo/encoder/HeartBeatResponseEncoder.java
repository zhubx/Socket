package com.neu.demo.encoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.response.HeartBeatResponse;

public class HeartBeatResponseEncoder implements IEncoder{

	private static final Logger LOG = LoggerFactory.getLogger(HeartBeatResponseEncoder.class);
	
	public ChannelBuffer doEncoder(ChannelHandlerContext ctx, Channel channel,
			Object msg) {
		LOG.info("[HeartBeatResponseEncoder]");
		HeartBeatResponse message = (HeartBeatResponse)msg;
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeByte(message.getCommandId());
		buffer.writeShort(message.getMsglength());
		buffer.writeByte(message.getResult());
		return buffer;
	}

}
