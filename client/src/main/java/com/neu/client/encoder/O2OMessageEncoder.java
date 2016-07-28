package com.neu.client.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.client.bean.O2OMessage;
import com.neu.client.encoder.IEncoder;
import com.neu.client.util.GlobalConstant;
import com.neu.client.util.StringUtil;

public class O2OMessageEncoder implements IEncoder<O2OMessage>{

	private static final Logger LOG = LoggerFactory.getLogger(O2OMessageEncoder.class);
			
	public void doEncode(ChannelHandlerContext arg0, O2OMessage message,
			List<Object> arg2) {
		LOG.info("[O2OMessageEncoder]" + message);
		ByteBuf buf = Unpooled.buffer();
		buf.writeByte(message.getCommandId());
		buf.writeShort(message.getMsglength());
		buf.writeBytes(StringUtil.get30String(message.getSender()).getBytes(Charset.forName(GlobalConstant.CHARSET_UTF8)));
		buf.writeBytes(StringUtil.get30String(message.getIncept()).getBytes(Charset.forName(GlobalConstant.CHARSET_UTF8)));
		buf.writeBytes(message.getMessage().getBytes());
		arg0.channel().writeAndFlush(buf);
	}


}
