package com.neu.demo.decoder;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neu.demo.bean.O2OMessage;
import com.neu.demo.util.GlobalConstant;

/**
 * 一对一消息解码
 * @author zhubx
 *
 */
public class O2OMessageDecoder implements IDecoder{

	private static final Logger LOG = LoggerFactory.getLogger(O2OMessageDecoder.class);
	
	public Object doDecode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) {
		LOG.info("[O2OMessageDecoder]" + channel);
		ChannelBuffer c = buffer.readBytes(buffer.readShort());
		O2OMessage message = new O2OMessage();
		String sender = c.readBytes(30).toString(Charset.forName(GlobalConstant.CHARSET_UTF8)).trim();
		String rname = c.readBytes(30).toString(Charset.forName(GlobalConstant.CHARSET_UTF8)).trim();
		String info = c.readBytes(c.readableBytes()).toString(Charset.forName(GlobalConstant.CHARSET_UTF8));
		message.setSender(sender);
		message.setIncept(rname);
		message.setMessage(info);
		return message;
	}

}
