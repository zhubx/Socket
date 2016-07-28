package com.neu.client.decoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class HearBeatResponseDecoder implements IDecoder{

	private static final Logger LOG = LoggerFactory.getLogger(HearBeatResponseDecoder.class);
			
	public Object doDecode(ChannelHandlerContext ctx, ByteBuf frame) {
		// TODO Auto-generated method stub
		LOG.info("[HearBeatResponseDecoder]");
		frame.readShort();
		frame.readByte();
		return null;
	}

}
