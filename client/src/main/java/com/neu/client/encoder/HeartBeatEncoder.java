package com.neu.client.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.client.bean.HeartBeat;

public class HeartBeatEncoder implements IEncoder<HeartBeat>{

	private static final Logger LOG = LoggerFactory.getLogger(HeartBeatEncoder.class);
	
	public void doEncode(ChannelHandlerContext arg0, HeartBeat message,
			List<Object> arg2) {
		// TODO Auto-generated method stub
		LOG.info("[HeartBeatEncoder]");
		ByteBuf buf = Unpooled.buffer();
		buf.writeByte(message.getCommandId());
		buf.writeShort(message.getMsglength());
		arg0.channel().writeAndFlush(buf);
	}

 

}
