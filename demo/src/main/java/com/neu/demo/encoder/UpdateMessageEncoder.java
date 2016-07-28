package com.neu.demo.encoder;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.bean.UpdateMessage;
import com.neu.demo.util.GlobalConstant;

public class UpdateMessageEncoder implements IEncoder{

	private static final Logger LOG = LoggerFactory.getLogger(UpdateMessageEncoder.class);
	
	public ChannelBuffer doEncoder(ChannelHandlerContext ctx, Channel channel,
			Object msg) {
		LOG.info("[UpdateMessageEncoder]");
		UpdateMessage message = (UpdateMessage)msg;
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeByte(message.getCommandId());
		buffer.writeShort(message.getMsglength());
		buffer.writeByte(message.getType());
		buffer.writeBytes(message.getName().getBytes(Charset.forName(GlobalConstant.CHARSET_UTF8)));
		return buffer;
	}

}
