package com.neu.demo.encoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;

import com.neu.demo.bean.ServerMessage;

public class ServerMessageEncoder implements IEncoder{

	public ChannelBuffer doEncoder(ChannelHandlerContext ctx, Channel channel,
			Object msg) {
		// TODO Auto-generated method stub
		ServerMessage message = (ServerMessage) msg;
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeByte(message.getCommandId());
		short len = (short) (1 + 30 + message.getContent().getBytes().length);
		buffer.writeShort(len);
		buffer.writeBytes(message.getSender().getBytes());
		buffer.writeBytes(message.getContent().getBytes());
		return buffer;
	}

}
