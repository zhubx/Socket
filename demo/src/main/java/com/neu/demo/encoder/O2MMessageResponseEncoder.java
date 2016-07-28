package com.neu.demo.encoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.response.O2MMessageResponse;

public class O2MMessageResponseEncoder implements IEncoder{

	private static final Logger LOG = LoggerFactory.getLogger(O2OMessageResponseEncoder.class);
	
	public ChannelBuffer doEncoder(ChannelHandlerContext ctx, Channel channel,
			Object msg) {
		// TODO Auto-generated method stub
		O2MMessageResponse message = (O2MMessageResponse)msg;
		LOG.info("[O2MMessageResponseEncoder]" + message.msgDetail());
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeByte(message.getCommandId());
		buffer.writeShort(message.getMsglength());
		buffer.writeByte(message.getResult());
		return buffer;
	}

}
