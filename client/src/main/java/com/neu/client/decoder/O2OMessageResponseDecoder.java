package com.neu.client.decoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.client.bean.O2OMessageResponse;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class O2OMessageResponseDecoder implements IDecoder{

	private static final Logger LOG = LoggerFactory.getLogger(O2OMessageResponseDecoder.class);
	
	public Object doDecode(ChannelHandlerContext ctx, ByteBuf frame) {
		LOG.info("[O2OMessageResponseDecoder]");
		O2OMessageResponse response = new O2OMessageResponse();
		frame.readShort();
		byte result = frame.readByte();
		response.setResult(result);
		return response;
	}

}
