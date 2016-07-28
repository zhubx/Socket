package com.neu.client.decoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.client.bean.O2MMessageResponse;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class O2MMessageResponseDecoder implements IDecoder{

	private static final Logger LOG = LoggerFactory.getLogger(O2MMessageResponseDecoder.class);
	
	public Object doDecode(ChannelHandlerContext ctx, ByteBuf frame) {
		// TODO Auto-generated method stub
		LOG.info("[O2MMessageResponseDecoder]");
		O2MMessageResponse response = new  O2MMessageResponse();
		frame.readShort();
		byte result = frame.readByte();
		response.setResult(result);
		
		return response;
	}

}
