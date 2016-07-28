package com.neu.client.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.Charset;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.client.bean.O2MMessage;
import com.neu.client.util.GlobalConstant;
import com.neu.client.util.StringUtil;

public class O2MMessageEncoder implements IEncoder<O2MMessage>{

	private static final Logger LOG = LoggerFactory.getLogger(O2MMessageEncoder.class);

	public void doEncode(ChannelHandlerContext arg0, O2MMessage message,
			List<Object> arg2) {
		// TODO Auto-generated method stub
		LOG.info("[O2MMessageEncoder]" + message);
		ByteBuf buf = Unpooled.buffer();
		buf.writeByte(message.getCommandId());
		buf.writeShort(message.getMsglength());
		buf.writeBytes(StringUtil.get30String(message.getSender()).getBytes(Charset.forName(GlobalConstant.CHARSET_UTF8)));
		buf.writeBytes(message.getMessage().getBytes(Charset.forName(GlobalConstant.CHARSET_UTF8)));
		arg0.channel().writeAndFlush(buf);
	}


	 

}
