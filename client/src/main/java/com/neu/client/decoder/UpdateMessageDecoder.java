package com.neu.client.decoder;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import com.neu.client.bean.UpdateMessage;
import com.neu.client.decoder.IDecoder;
import com.neu.client.util.GlobalConstant;

public class UpdateMessageDecoder implements IDecoder{

	private static final Logger LOG = LoggerFactory.getLogger(UpdateMessageDecoder.class);
	
	public Object doDecode(ChannelHandlerContext ctx, ByteBuf frame) {
		LOG.info("[UpdateMessageDecoder]");		
		UpdateMessage message = new UpdateMessage();
		frame.readShort();
		message.setType(frame.readByte());
		message.setName(frame.readBytes(frame.readableBytes()).toString(Charset.forName(GlobalConstant.CHARSET_UTF8)).trim());
		return message;
	}

}
