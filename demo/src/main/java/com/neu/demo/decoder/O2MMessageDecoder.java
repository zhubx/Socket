package com.neu.demo.decoder;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;

import com.neu.demo.bean.O2MMessage;
import com.neu.demo.util.GlobalConstant;

public class O2MMessageDecoder implements IDecoder{

	public Object doDecode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) {
		// TODO Auto-generated method stub
		short len = buffer.readShort();
		String sender = buffer.readBytes(30).toString(Charset.forName(GlobalConstant.CHARSET_UTF8)).trim();
		String message = buffer.readBytes(len - 30).toString(Charset.forName(GlobalConstant.CHARSET_UTF8));
		O2MMessage m = new O2MMessage();
		m.setMsglength(len);
		m.setCommandId((byte)0x0003);
		m.setSender(sender);
		m.setMessage(message);
		return m;
	}

}
