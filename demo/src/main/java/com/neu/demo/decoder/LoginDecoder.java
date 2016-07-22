package com.neu.demo.decoder;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;

import com.neu.demo.bean.LoginMessage;
import com.neu.demo.util.GlobalConstant;

public class LoginDecoder implements IDecoder{

	public Object doDecode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) {
		// TODO Auto-generated method stub
		ChannelBuffer c = buffer.readBytes(buffer.readShort());
		String[] body = c.toString(Charset.forName(GlobalConstant.CHARSET_UTF8)).split(" ");
		LoginMessage message = new LoginMessage(body[0],body[1]);
		return message;
	}

}
